CREATE TABLE IF NOT EXISTS flyway_schema_history
(
    installed_rank INTEGER                                   NOT NULL,
    version        VARCHAR(50),
    description    VARCHAR(200)                              NOT NULL,
    type           VARCHAR(20)                               NOT NULL,
    script         VARCHAR(1000)                             NOT NULL,
    checksum       INTEGER,
    installed_by   VARCHAR(100)                              NOT NULL,
    installed_on   TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    execution_time INTEGER                                   NOT NULL,
    success        BOOLEAN                                   NOT NULL,
    CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);
-- changeset Владимир:1719232864425-1
CREATE TABLE chat_room (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, CONSTRAINT pk_chat_room PRIMARY KEY (id));

-- changeset Владимир:1719232864425-2
CREATE TABLE chat_room_users (chat_room_id BIGINT NOT NULL, user_id BIGINT NOT NULL, CONSTRAINT pk_chat_room_users PRIMARY KEY (chat_room_id, user_id));

-- changeset Владимир:1719232864425-3
CREATE TABLE messages (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, sender_id BIGINT, receiver_id BIGINT, content VARCHAR(255), timestamp TIMESTAMP WITHOUT TIME ZONE, chat_room_id BIGINT, CONSTRAINT pk_messages PRIMARY KEY (id));

-- changeset Владимир:1719232864425-4
CREATE TABLE users (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, username VARCHAR(255), password VARCHAR(255), active BOOLEAN NOT NULL, CONSTRAINT pk_users PRIMARY KEY (id));

-- changeset Владимир:1719232864425-5
ALTER TABLE messages ADD CONSTRAINT FK_MESSAGES_ON_CHAT_ROOM FOREIGN KEY (chat_room_id) REFERENCES chat_room (id);

-- changeset Владимир:1719232864425-6
ALTER TABLE messages ADD CONSTRAINT FK_MESSAGES_ON_RECEIVER FOREIGN KEY (receiver_id) REFERENCES users (id);

-- changeset Владимир:1719232864425-7
ALTER TABLE messages ADD CONSTRAINT FK_MESSAGES_ON_SENDER FOREIGN KEY (sender_id) REFERENCES users (id);

-- changeset Владимир:1719232864425-8
ALTER TABLE chat_room_users ADD CONSTRAINT fk_charoouse_on_chat_room FOREIGN KEY (chat_room_id) REFERENCES chat_room (id);

-- changeset Владимир:1719232864425-9
ALTER TABLE chat_room_users ADD CONSTRAINT fk_charoouse_on_user FOREIGN KEY (user_id) REFERENCES users (id);

