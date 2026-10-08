# Seguridad JWT y autorización

## Alcance

Spring Security protege transversalmente los 52 bounded contexts sin introducir
dependencias de seguridad en sus agregados. La implementación adapta el patrón
del repositorio de referencia `trainingLeader/security` a la arquitectura
multimódulo, PostgreSQL, Flyway y `ddl-auto: validate` de este proyecto.

La solución proporciona:

- access tokens JWT firmados con HMAC SHA-256;
- refresh tokens aleatorios persistidos y revocables;
- contraseñas almacenadas con BCrypt;
- sesiones stateless;
- autorización basada en `ROLE_USER`, `ROLE_MODERATOR` y `ROLE_ADMIN`;
- respuestas JSON para errores `401 Unauthorized` y `403 Forbidden`;
- CORS restringido a los orígenes configurados.

## Capas

```text
domain/security
├── model       # SecurityUser, Role, RefreshToken y TokenPrincipal
├── port        # Repositorios, PasswordService y TokenService
└── exception   # Errores de credenciales y dominio

application/security
├── command
├── dto
└── usecase     # Registro, login, refresh, logout, perfil, contraseña y roles

infrastructure/security
├── adapters/in/rest
├── adapters/out/persistence
├── config
├── exception
├── filter
└── service
```

El dominio y la aplicación desconocen Spring Security y JJWT. Las dependencias
técnicas se encuentran exclusivamente en `infrastructure`.

## Tablas técnicas

Flyway administra cuatro tablas adicionales:

| Migración | Tabla | Responsabilidad |
| --- | --- | --- |
| V53 | `security_roles` | Catálogo de roles y authorities |
| V54 | `security_users` | Credenciales BCrypt y estado del usuario |
| V55 | `security_user_roles` | Asignación de roles mediante UUID escalares |
| V56 | `security_refresh_tokens` | Sesiones renovables y revocables |

Estas tablas son infraestructura transversal. Los bounded contexts de negocio
siguen siendo los 52 definidos por `V1`–`V52`.

No se usan `@ManyToMany`, `@ManyToOne` ni otras asociaciones JPA. Las relaciones
de seguridad se resuelven mediante repositorios y UUID, igual que las relaciones
entre los agregados clínicos.

## Política de acceso

| Ruta | Acceso |
| --- | --- |
| `POST /api/auth/register` | Público; asigna `ROLE_USER` |
| `POST /api/auth/login` | Público |
| `POST /api/auth/refresh` | Público con refresh token válido |
| `/api/auth/**` restante | Cualquier usuario autenticado |
| `/api/security/**` | Solo `ROLE_ADMIN` |
| Las 52 rutas `/api/**` de negocio | `ROLE_MODERATOR` o `ROLE_ADMIN` |
| Cualquier otra ruta | Usuario autenticado |

`ROLE_USER` permite mantener la cuenta y la sesión, pero no leer ni modificar
información clínica. Esta separación evita que un registro público obtenga
acceso automático a los 52 contextos.

## Configuración obligatoria

El secreto JWT no tiene valor predeterminado y debe contener al menos 32 bytes.
Para generar uno temporal en PowerShell:

```powershell
$jwtBytes = New-Object byte[] 48
[Security.Cryptography.RandomNumberGenerator]::Fill($jwtBytes)
$env:JWT_SECRET = [Convert]::ToBase64String($jwtBytes)
```

Variables disponibles:

| Variable | Predeterminado | Descripción |
| --- | --- | --- |
| `JWT_SECRET` | Sin valor | Secreto privado obligatorio |
| `JWT_ISSUER` | `back-intro` | Emisor exigido al validar el token |
| `JWT_ACCESS_TOKEN_EXPIRATION` | `900000` | Access token: 15 minutos |
| `JWT_REFRESH_TOKEN_EXPIRATION` | `604800000` | Refresh token: 7 días |
| `CORS_ALLOWED_ORIGINS` | Frontends locales | Orígenes separados por coma |

No se debe registrar `JWT_SECRET`, contraseñas ni tokens en Git, logs o tickets.

## Flujo de uso

### Registro

```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "operator@example.com",
  "password": "una-clave-segura"
}
```

El usuario se crea activo con `ROLE_USER` y su contraseña se almacena como hash
BCrypt. La respuesta nunca contiene el hash.

### Promoción inicial de un administrador

No se incluye una contraseña administrativa predeterminada. Después de registrar
la primera cuenta, un administrador de PostgreSQL puede asignarle `ROLE_ADMIN`
una única vez:

```sql
INSERT INTO librarydb_schema.security_user_roles (user_id, role_id)
SELECT security_user.id, security_role.id
FROM librarydb_schema.security_users security_user
CROSS JOIN librarydb_schema.security_roles security_role
WHERE security_user.email = 'admin@example.com'
  AND security_role.authority = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;
```

Desde ese momento, el propio administrador puede usar:

```http
PUT /api/security/users/{userId}/roles
Authorization: Bearer <access-token-admin>
Content-Type: application/json

{
  "roleName": "MODERATOR"
}
```

El usuario promovido debe iniciar sesión nuevamente o renovar su access token
para recibir el nuevo authority dentro del JWT.

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "operator@example.com",
  "password": "una-clave-segura"
}
```

La respuesta incluye `accessToken`, `refreshToken`, `tokenType` y
`expiresInSeconds`. Para consumir cualquiera de los 52 contextos:

```http
GET /api/countries
Authorization: Bearer <accessToken>
```

### Renovación y cierre de sesión

```http
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "<refreshToken>"
}
```

```http
POST /api/auth/logout
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "refreshToken": "<refreshToken>"
}
```

El logout revoca el refresh token. El access token actual conserva validez hasta
su expiración máxima de 15 minutos. El cambio de contraseña elimina todos los
refresh tokens del usuario antes de persistir el nuevo hash.

## Respuestas de autorización

- `401 Unauthorized`: falta el Bearer token, está vencido o su firma/emisor no
  es válido.
- `403 Forbidden`: el token es válido, pero no contiene el rol requerido.
- `400 Bad Request`: el registro, la contraseña o el rol solicitado incumplen
  una regla de dominio.

## Verificación

Las pruebas focalizadas cubren:

- generación, firma y lectura de JWT;
- independencia de refresh tokens;
- registro, login, refresh y cambio de contraseña;
- acceso público, autenticado, moderador y administrador.

```powershell
mvn -pl infrastructure -am `
  "-Dtest=JwtTokenServiceTest,SecurityUseCaseTest,SecurityPolicyTest" `
  "-Dsurefire.failIfNoSpecifiedTests=false" test
```
