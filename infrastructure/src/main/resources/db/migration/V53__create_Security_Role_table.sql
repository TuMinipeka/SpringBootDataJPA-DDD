CREATE TABLE IF NOT EXISTS ${db_schema}.security_roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL,
    authority VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_security_roles_name UNIQUE (name),
    CONSTRAINT uk_security_roles_authority UNIQUE (authority)
);

INSERT INTO ${db_schema}.security_roles (id, name, authority)
VALUES
    ('550e8400-e29b-41d4-a716-446655440001', 'USER', 'ROLE_USER'),
    ('550e8400-e29b-41d4-a716-446655440002', 'ADMIN', 'ROLE_ADMIN'),
    ('550e8400-e29b-41d4-a716-446655440003', 'MODERATOR', 'ROLE_MODERATOR')
ON CONFLICT DO NOTHING;
