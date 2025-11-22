CREATE SEQUENCE IF NOT EXISTS retro_columns_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS retro_items_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS role_permissions_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE permissions
(
    id         UUID                        NOT NULL,
    version    INTEGER                     NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT "pk_permıssıons" PRIMARY KEY (id)
);

CREATE TABLE retro_columns
(
    id           BIGINT                      NOT NULL,
    version      INTEGER                     NOT NULL,
    retro_id     UUID                        NOT NULL,
    title        VARCHAR(100)                NOT NULL,
    color        VARCHAR(7)                  NOT NULL,
    column_order INTEGER                     NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by   VARCHAR(100),
    updated_by   VARCHAR(100),
    CONSTRAINT pk_retro_columns PRIMARY KEY (id)
);

CREATE TABLE retro_items
(
    id                 BIGINT                      NOT NULL,
    version            INTEGER                     NOT NULL,
    column_id          BIGINT                      NOT NULL,
    created_by_user_id UUID                        NOT NULL,
    content            VARCHAR(500)                NOT NULL,
    vote_count         INTEGER                     NOT NULL,
    is_visible         BOOLEAN                     NOT NULL,
    created_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(100),
    updated_by         VARCHAR(100),
    CONSTRAINT "pk_retro_ıtems" PRIMARY KEY (id)
);

CREATE TABLE retro_participants
(
    retro_id UUID NOT NULL,
    user_id  UUID NOT NULL,
    CONSTRAINT "pk_retro_partıcıpants" PRIMARY KEY (retro_id, user_id)
);

CREATE TABLE retros
(
    id                 UUID                        NOT NULL,
    version            INTEGER                     NOT NULL,
    title              VARCHAR(255)                NOT NULL,
    description        VARCHAR(1000),
    team_id            UUID                        NOT NULL,
    created_by_user_id UUID                        NOT NULL,
    status             VARCHAR(255)                NOT NULL,
    scheduled_date     TIMESTAMP WITHOUT TIME ZONE,
    created_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(100),
    updated_by         VARCHAR(100),
    CONSTRAINT pk_retros PRIMARY KEY (id)
);

CREATE TABLE role_permissions
(
    id            BIGINT                      NOT NULL,
    version       INTEGER                     NOT NULL,
    role_id       UUID,
    permission_id UUID,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by    VARCHAR(100),
    updated_by    VARCHAR(100),
    CONSTRAINT "pk_role_permıssıons" PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id         UUID                        NOT NULL,
    version    INTEGER                     NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE teams
(
    id              UUID                        NOT NULL,
    version         INTEGER                     NOT NULL,
    name            VARCHAR(255)                NOT NULL,
    status          VARCHAR(255)                NOT NULL,
    scrum_master_id UUID,
    emergency_code  VARCHAR(255)                NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by      VARCHAR(100),
    updated_by      VARCHAR(100),
    CONSTRAINT pk_teams PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         UUID                        NOT NULL,
    version    INTEGER                     NOT NULL,
    email      VARCHAR(255)                NOT NULL,
    password   VARCHAR(255)                NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    role_id    UUID,
    team_id    UUID,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE permissions
    ADD CONSTRAINT "uc_permıssıons_name" UNIQUE (name);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE teams
    ADD CONSTRAINT uc_teams_scrum_master UNIQUE (scrum_master_id);

ALTER TABLE users
    ADD CONSTRAINT "uc_users_emaıl" UNIQUE (email);

ALTER TABLE retros
    ADD CONSTRAINT FK_RETROS_ON_CREATED_BY_USER FOREIGN KEY (created_by_user_id) REFERENCES users (id);

ALTER TABLE retros
    ADD CONSTRAINT FK_RETROS_ON_TEAM FOREIGN KEY (team_id) REFERENCES teams (id);

ALTER TABLE retro_columns
    ADD CONSTRAINT FK_RETRO_COLUMNS_ON_RETRO FOREIGN KEY (retro_id) REFERENCES retros (id);

ALTER TABLE retro_items
    ADD CONSTRAINT FK_RETRO_ITEMS_ON_COLUMN FOREIGN KEY (column_id) REFERENCES retro_columns (id);

ALTER TABLE retro_items
    ADD CONSTRAINT FK_RETRO_ITEMS_ON_CREATED_BY_USER FOREIGN KEY (created_by_user_id) REFERENCES users (id);

ALTER TABLE role_permissions
    ADD CONSTRAINT FK_ROLE_PERMISSIONS_ON_PERMISSION FOREIGN KEY (permission_id) REFERENCES permissions (id);

ALTER TABLE role_permissions
    ADD CONSTRAINT FK_ROLE_PERMISSIONS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE teams
    ADD CONSTRAINT FK_TEAMS_ON_SCRUM_MASTER FOREIGN KEY (scrum_master_id) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_TEAM FOREIGN KEY (team_id) REFERENCES teams (id);

ALTER TABLE retro_participants
    ADD CONSTRAINT fk_retpar_on_retro FOREIGN KEY (retro_id) REFERENCES retros (id);

ALTER TABLE retro_participants
    ADD CONSTRAINT fk_retpar_on_user FOREIGN KEY (user_id) REFERENCES users (id);