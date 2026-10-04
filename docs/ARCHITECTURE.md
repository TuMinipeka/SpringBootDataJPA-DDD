# Arquitectura técnica

## Alcance

El backend implementa los 52 bounded contexts derivados de las migraciones
`V1`–`V52`. Cada tabla constituye una vertical funcional independiente y sigue
la estructura definida originalmente por `country`, sin introducir un modelo
arquitectónico alternativo.

El sistema combina dos responsabilidades complementarias:

- Flyway crea, versiona y evoluciona el esquema PostgreSQL.
- JPA/Hibernate valida los mapeos y persiste los agregados durante la ejecución.

Hibernate usa `ddl-auto: validate`; por tanto, no crea ni modifica tablas.

## Dirección de dependencias

```text
HTTP / Spring MVC
        |
        v
infrastructure  -->  application  -->  domain
        |                                ^
        +---- adaptador JPA --------------+
        |
        v
PostgreSQL
```

`domain` no depende de Spring, JPA ni de los otros módulos. `application`
orquesta los puertos del dominio. `infrastructure` contiene los adaptadores de
entrada y salida y ensambla las dependencias mediante configuración Spring.

## Contrato estructural de cada bounded context

Cada paquete reproduce la misma vertical:

```text
domain/<context>/
├── event/                       # Registered, Updated y Deleted
├── model/aggregate/             # Aggregate Root
├── model/valueobject/           # ID tipado basado en UUID
└── port/repository/             # Puerto de persistencia

application/<context>/
├── command/                     # Register y Update
├── dto/                         # Response
├── exception/                   # NotFoundApplicationException
└── usecase/                     # Register, GetById, List, Update y Delete

infrastructure/<context>/
├── adapters/in/rest/            # Controller y dos Request DTO
├── adapters/out/persistence/    # Entity, mapper, repository y adapter
└── config/                      # Ensamblado de casos de uso
```

La API REST de cada contexto ofrece el mismo contrato sobre su ruta base:

| Operación | Método y ruta | Resultado esperado |
| --- | --- | --- |
| Registrar | `POST /api/<resource>` | `201 Created` |
| Listar | `GET /api/<resource>` | `200 OK` |
| Consultar | `GET /api/<resource>/{id}` | `200 OK` |
| Actualizar | `PUT /api/<resource>/{id}` | `200 OK` |
| Eliminar | `DELETE /api/<resource>/{id}` | `204 No Content` |

## Relaciones entre contextos

Los agregados no contienen entidades JPA de otros contextos. Una relación se
representa de la siguiente manera:

- el dominio usa el value object de identidad del contexto relacionado;
- la aplicación valida la existencia de las referencias requeridas antes de
  registrar o actualizar;
- la entidad JPA almacena la relación como una columna UUID escalar;
- las claves foráneas y sus índices siguen definidos por Flyway.

Esta decisión evita acoplamiento entre agregados, cargas perezosas accidentales
y cascadas no controladas. La auditoría no encontró anotaciones `@ManyToOne`,
`@OneToMany`, `@OneToOne`, `@ManyToMany` ni `@JoinColumn`.

## Entidades JPA y ciclo de vida

Cada entidad JPA incluye un constructor sin argumentos `protected`. JPA lo
necesita para reconstruir objetos mediante reflexión; no forma parte de la API
pública del agregado. Los constructores públicos y el método de sincronización
son utilizados por el mapper y el adaptador.

Cuando la tabla contiene marcas temporales, `@PrePersist` inicializa las fechas
de creación/actualización y `@PreUpdate` renueva la fecha de actualización. Los
campos inmutables se declaran con `updatable = false` cuando corresponde al SQL.

## Flujo de una operación

```text
Request validado
    -> Command
    -> Use Case
    -> Aggregate / Repository Port
    -> Repository Adapter
    -> Persistence Mapper
    -> Spring Data JPA
    -> PostgreSQL
```

En sentido inverso, el mapper restaura el agregado sin generar un nuevo evento
de registro y el caso de uso construye el DTO de respuesta.

## Catálogo de bounded contexts

| Migración | Tabla | Paquete | Ruta base |
| --- | --- | --- | --- |
| V1 | `countries` | `country` | `/api/countries` |
| V2 | `state_regions` | `stateregion` | `/api/state-regions` |
| V3 | `city_municipalities` | `citymunicipality` | `/api/city-municipalities` |
| V4 | `genders` | `gender` | `/api/genders` |
| V5 | `document_types` | `documenttype` | `/api/document-types` |
| V6 | `professional_types` | `professionaltype` | `/api/professional-types` |
| V7 | `relationship_types` | `relationshiptype` | `/api/relationship-types` |
| V8 | `contacts` | `contact` | `/api/contacts` |
| V9 | `phone_contacts` | `phonecontact` | `/api/phone-contacts` |
| V10 | `email_contacts` | `emailcontact` | `/api/email-contacts` |
| V11 | `professionals` | `professional` | `/api/professionals` |
| V12 | `patients` | `patient` | `/api/patients` |
| V13 | `patient_contacts` | `patientcontact` | `/api/patient-contacts` |
| V14 | `studies` | `study` | `/api/studies` |
| V15 | `professional_studies` | `professionalstudy` | `/api/professional-studies` |
| V16 | `clinical_record_statuses` | `clinicalrecordstatus` | `/api/clinical-record-statuses` |
| V17 | `clinical_records` | `clinicalrecord` | `/api/clinical-records` |
| V18 | `encounter_types` | `encountertype` | `/api/encounter-types` |
| V19 | `encounter_modalities` | `encountermodality` | `/api/encounter-modalities` |
| V20 | `encounter_statuses` | `encounterstatus` | `/api/encounter-statuses` |
| V21 | `encounters` | `encounter` | `/api/encounters` |
| V22 | `clinical_notes` | `clinicalnote` | `/api/clinical-notes` |
| V23 | `mental_status_exams` | `mentalstatusexam` | `/api/mental-status-exams` |
| V24 | `risk_levels` | `risklevel` | `/api/risk-levels` |
| V25 | `risk_assessments` | `riskassessment` | `/api/risk-assessments` |
| V26 | `treatment_statuses` | `treatmentstatus` | `/api/treatment-statuses` |
| V27 | `treatment_plans` | `treatmentplan` | `/api/treatment-plans` |
| V28 | `treatment_goal_statuses` | `treatmentgoalstatus` | `/api/treatment-goal-statuses` |
| V29 | `treatment_goals` | `treatmentgoal` | `/api/treatment-goals` |
| V30 | `medication_routes` | `medicationroute` | `/api/medication-routes` |
| V31 | `assessment_types` | `assessmenttype` | `/api/assessment-types` |
| V32 | `consent_types` | `consenttype` | `/api/consent-types` |
| V33 | `diagnostic_systems` | `diagnosticsystem` | `/api/diagnostic-systems` |
| V34 | `conversation_statuses` | `conversationstatus` | `/api/conversation-statuses` |
| V35 | `priorities` | `priority` | `/api/priorities` |
| V36 | `sender_types` | `sendertype` | `/api/sender-types` |
| V37 | `message_types` | `messagetype` | `/api/message-types` |
| V38 | `ai_runs_statuses` | `airunstatus` | `/api/ai-run-statuses` |
| V39 | `escalations_statuses` | `escalationstatus` | `/api/escalation-statuses` |
| V40 | `provider_models_ai` | `aiprovidermodel` | `/api/ai-provider-models` |
| V41 | `ai_models` | `aimodel` | `/api/ai-models` |
| V42 | `chat_conversations` | `chatconversation` | `/api/chat-conversations` |
| V43 | `chat_participants` | `chatparticipant` | `/api/chat-participants` |
| V44 | `chat_messages` | `chatmessage` | `/api/chat-messages` |
| V45 | `chat_conversation_ai_settings` | `chatconversationaisetting` | `/api/chat-conversation-ai-settings` |
| V46 | `chat_ai_runs` | `chatairun` | `/api/chat-ai-runs` |
| V47 | `chat_ai_run_metrics` | `chatairunmetric` | `/api/chat-ai-run-metrics` |
| V48 | `chat_ai_run_errors` | `chatairunerror` | `/api/chat-ai-run-errors` |
| V49 | `chat_escalations` | `chatescalation` | `/api/chat-escalations` |
| V50 | `chat_escalation_assignments` | `chatescalationassignment` | `/api/chat-escalation-assignments` |
| V51 | `chat_escalation_status_history` | `chatescalationstatushistory` | `/api/chat-escalation-status-history` |
| V52 | `patient_allergies` | `patientallergy` | `/api/patient-allergies` |

## Estrategia de pruebas

Cada contexto tiene dos clases de prueba y tres métodos focalizados:

- una prueba del mapper para comprobar el recorrido dominio–JPA–dominio;
- dos pruebas del adaptador para comprobar creación y sincronización.

La suite completa contiene 156 pruebas, equivalentes a 3 por contexto. El
comando de verificación es:

```powershell
mvn test
```

La auditoría final del 4 de octubre de 2026 verificó:

- 52 migraciones consecutivas y 52 tablas;
- 52 agregados, IDs tipados y puertos de repositorio;
- 260 casos de uso, cinco por contexto;
- 52 entidades JPA y correspondencia exacta de sus 385 columnas;
- 52 controladores con el contrato CRUD completo;
- 156 pruebas superadas, sin fallos, errores ni omisiones.

## Incorporación de un cambio futuro

Un nuevo contexto debe partir de una migración posterior a `V52` y conservar el
mismo recorrido vertical. El orden recomendado es:

1. Crear la migración sin modificar versiones ya aplicadas.
2. Modelar agregado, ID, eventos y puerto en `domain`.
3. Implementar comandos, respuesta, excepción y cinco casos de uso.
4. Añadir entidad, mapper, repositorio, adaptador, controlador y configuración.
5. Comprobar todas las columnas SQL y validar referencias mediante puertos.
6. Añadir las pruebas focalizadas del mapper y del adaptador.
7. Ejecutar `mvn test` desde la raíz.

Los detalles operativos de PostgreSQL y Flyway están en
[`../infrastructure/DATABASE.md`](../infrastructure/DATABASE.md).
