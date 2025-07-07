DO
$do$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'aet') THEN
        CREATE ROLE aet WITH LOGIN PASSWORD 'aet';
        GRANT CONNECT ON DATABASE aet TO aet;
    END IF;
END
$do$;

BEGIN;

CREATE SCHEMA IF NOT EXISTS sc_aet;
ALTER SCHEMA sc_aet OWNER TO aet;

DROP TABLE IF EXISTS sc_aet."login", sc_aet."jwt", sc_aet."delay_login", sc_aet."game_image", sc_aet."game", sc_aet."role_player", sc_aet."role", sc_aet."family_player", sc_aet."family",  sc_aet."owner_account", sc_aet."player" CASCADE;

-- Joueur --
CREATE TABLE if NOT EXISTS sc_aet.player(
    "id" BIGINT PRIMARY KEY,
    "nickname" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);

-- PROPRIETAIRE --
CREATE TABLE if NOT EXISTS sc_aet.owner_account(
    "owner_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "email" VARCHAR(255) NOT NULL UNIQUE,
    "password" TEXT NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_owner_account_email ON sc_aet.owner_account(email);
CREATE INDEX IF NOT EXISTS idx_owner_account_id ON sc_aet.owner_account(owner_id);

-- FAMILLE --
CREATE TABLE if NOT EXISTS sc_aet.family(
    "id" BIGINT PRIMARY KEY,
    "owner_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_family_owner ON sc_aet.family(owner_id);

-- LIAISON JOEUR AVEC FAMILLE  --
CREATE TABLE if NOT EXISTS sc_aet.family_player(
    "id" BIGINT PRIMARY KEY,
    "family_id" BIGINT NOT NULL REFERENCES sc_aet."family"("id") on delete cascade,
    "player_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_family_player_family ON sc_aet.family_player(family_id);
CREATE INDEX IF NOT EXISTS idx_family_player_player ON sc_aet.family_player(player_id);

-- Role --
create table IF NOT EXISTS sc_aet.role(
    "id" INT PRIMARY KEY,
    "role" TEXT NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);

-- Liasion Role AVEC  JOEUR --
create table IF NOT EXISTS sc_aet.role_player(
    "id" BIGINT PRIMARY KEY,
    "player_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "role_id" INT NOT NULL REFERENCES sc_aet."role"("id") on delete cascade,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_role_user ON sc_aet.role_player(player_id, role_id);

-- JEU --
CREATE TABLE if NOT EXISTS sc_aet.game(
    "id" INT PRIMARY KEY,
    "name" VARCHAR(255),
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);

-- IMAGE DE JEUX --
CREATE TABLE if NOT EXISTS sc_aet.game_image(
    "id" BIGINT PRIMARY KEY,
    "game_id" INT NOT NULL REFERENCES sc_aet."game"("id") on delete cascade,
    "image_path" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_image_product ON sc_aet.game_image(game_id);

-- JWT --
CREATE TABLE if NOT EXISTS sc_aet.jwt(
    "id" BIGINT PRIMARY KEY,
    "owner_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "email" TEXT NOT NULL,
    "jwt_token" TEXT NOT NULL,
    "jwt_id" TEXT NOT NULL,
    "is_valid" BOOLEAN NOT NULL DEFAULT FALSE,
    "expired_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_jwt ON sc_aet.jwt(owner_id);

-- Connexion utilisateur --
create table IF NOT EXISTS sc_aet.login(
    "id" BIGINT PRIMARY KEY,
    "owner_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "is_login_success" BOOLEAN NOT NULL,
    "has_to_be_check" BOOLEAN NOT NULL DEFAULT TRUE,
    "login_at" TIMESTAMPTZ NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_login ON sc_aet.login(owner_id);

-- Delai de connexion au compte --
create table IF NOT EXISTS sc_aet.delay_login(
    "id" BIGINT PRIMARY KEY,
    "owner_id" BIGINT NOT NULL REFERENCES sc_aet."player"("id") on delete cascade,
    "delay_login_until" TIMESTAMPTZ NOT NULL DEFAULT NOW() + INTERVAL '5 minutes',
    "created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
    "updated_at" TIMESTAMPTZ
);
CREATE INDEX IF NOT EXISTS idx_delay_login ON sc_aet.delay_login(owner_id);


ALTER TABLE IF EXISTS sc_aet.player OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.owner_account OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.family OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.family_player OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.role OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.role_player OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.game OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.game_image OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.jwt OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.login OWNER TO aet;
ALTER TABLE IF EXISTS sc_aet.delay_login OWNER TO aet;

GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.player TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.owner_account TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.family TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.family_player TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.role TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.role_player TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.game TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.game_image TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.jwt TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.login TO aet;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE sc_aet.delay_login TO aet;

CREATE SEQUENCE IF NOT EXISTS sc_aet.player_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.family_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.family_player_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.role_player_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.game_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.game_image_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.jwt_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.login_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS sc_aet.delay_login_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

ALTER SEQUENCE IF EXISTS sc_aet.player_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.family_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.family_player_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.role_player_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.game_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.game_image_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.jwt_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.login_id_seq OWNER TO aet;
ALTER SEQUENCE IF EXISTS sc_aet.delay_login_id_seq OWNER TO aet;

ALTER SEQUENCE IF EXISTS sc_aet.player_id_seq OWNED BY sc_aet.player.id;
ALTER SEQUENCE IF EXISTS sc_aet.family_id_seq OWNED BY sc_aet.family.id;
ALTER SEQUENCE IF EXISTS sc_aet.family_player_id_seq OWNED BY sc_aet.family_player.id;
ALTER SEQUENCE IF EXISTS sc_aet.role_player_id_seq OWNED BY sc_aet.role_player.id;
ALTER SEQUENCE IF EXISTS sc_aet.game_id_seq OWNED BY sc_aet.game.id;
ALTER SEQUENCE IF EXISTS sc_aet.game_image_id_seq OWNED BY sc_aet.game_image.id;
ALTER SEQUENCE IF EXISTS sc_aet.jwt_id_seq OWNED BY sc_aet.jwt.id;
ALTER SEQUENCE IF EXISTS sc_aet.login_id_seq OWNED BY sc_aet.login.id;
ALTER SEQUENCE IF EXISTS sc_aet.delay_login_id_seq OWNED BY sc_aet.delay_login.id;

ALTER TABLE sc_aet.player ALTER COLUMN id SET DEFAULT nextval('sc_aet.player_id_seq');
ALTER TABLE sc_aet.family ALTER COLUMN id SET DEFAULT nextval('sc_aet.family_id_seq');
ALTER TABLE sc_aet.family_player ALTER COLUMN id SET DEFAULT nextval('sc_aet.family_player_id_seq');
ALTER TABLE sc_aet.role_player ALTER COLUMN id SET DEFAULT nextval('sc_aet.role_player_id_seq');
ALTER TABLE sc_aet.game ALTER COLUMN id SET DEFAULT nextval('sc_aet.game_id_seq');
ALTER TABLE sc_aet.game_image ALTER COLUMN id SET DEFAULT nextval('sc_aet.game_image_id_seq');
ALTER TABLE sc_aet.jwt ALTER COLUMN id SET DEFAULT nextval('sc_aet.jwt_id_seq');
ALTER TABLE sc_aet.login ALTER COLUMN id SET DEFAULT nextval('sc_aet.login_id_seq');
ALTER TABLE sc_aet.delay_login ALTER COLUMN id SET DEFAULT nextval('sc_aet.delay_login_id_seq');

INSERT INTO sc_aet.player ("nickname") VALUES
('playerA'),
('playerB'),
('playerC'),
('playerD'),
('Admin1');

INSERT INTO sc_aet.owner_account ("owner_id", "email" , "password") VALUES
(2, 'client@hotmail.fr', '$2y$10$9PSCTWQiEIbXulYGOZi7.u6x5S6.8XuM0dL3EH72sigNHLlUW2wzy'),
(4, 'clientZ@hotmail.fr', '$2y$10$9PSCTWQiEIbXulYGOZi7.u6x5S6.8XuM0dL3EH72sigNHLlUW2wzy'),
(5, 'clientZ@hotmail.fr', '$2y$10$9PSCTWQiEIbXulYGOZi7.u6x5S6.8XuM0dL3EH72sigNHLlUW2wzy');

INSERT INTO sc_aet.role ("id", "role") VALUES
(1, 'ROLE_PLAYER'),
(2, 'ROLE_OWNER'),
(3, 'ROLE_ADMIN');

INSERT INTO sc_aet.role_player ("player_id", "role_id") VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(4, 2),
(5, 1),
(5, 2),
(5, 3);

COMMIT;