CREATE TABLE IF NOT EXISTS ${db_schema}.security_user_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    assigned_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_security_user_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_security_user_roles_user FOREIGN KEY (user_id)
        REFERENCES ${db_schema}.security_users(id) ON DELETE CASCADE,
    CONSTRAINT fk_security_user_roles_role FOREIGN KEY (role_id)
        REFERENCES ${db_schema}.security_roles(id) ON DELETE RESTRICT
);

CREATE INDEX idx_security_user_roles_role_id
    ON ${db_schema}.security_user_roles(role_id);
