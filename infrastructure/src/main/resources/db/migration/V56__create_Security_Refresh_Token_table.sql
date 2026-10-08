CREATE TABLE IF NOT EXISTS ${db_schema}.security_refresh_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    token VARCHAR(255) NOT NULL,
    expires_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_security_refresh_tokens_token UNIQUE (token),
    CONSTRAINT fk_security_refresh_tokens_user FOREIGN KEY (user_id)
        REFERENCES ${db_schema}.security_users(id) ON DELETE CASCADE
);

CREATE INDEX idx_security_refresh_tokens_user_id
    ON ${db_schema}.security_refresh_tokens(user_id);

CREATE INDEX idx_security_refresh_tokens_expires_at
    ON ${db_schema}.security_refresh_tokens(expires_at);
