CREATE TYPE valid_items AS ENUM ('ITEM', 'COIN', 'CASH');

CREATE TABLE item
(
    item_name VARCHAR(255) PRIMARY KEY,
    type      valid_items NOT NULL
);

CREATE TABLE country
(
    country_code VARCHAR(50) PRIMARY KEY
);

CREATE TABLE level
(
    level INT PRIMARY KEY
);

CREATE TABLE campaign
(
    name         VARCHAR(255) PRIMARY KEY,
    game         VARCHAR(255) NOT NULL,
    priority     DECIMAL      NOT NULL,
    start_date   TIMESTAMP    NOT NULL,
    end_date     TIMESTAMP    NOT NULL,
    enabled      BOOLEAN      NOT NULL,
    last_updated TIMESTAMP    NOT NULL
);

CREATE TABLE campaign_level
(
    id            SERIAL PRIMARY KEY,
    campaign_name VARCHAR(255),
    min_level     INT,
    max_level     INT,
    FOREIGN KEY (campaign_name) REFERENCES campaign (name)
);

CREATE TABLE campaign_country
(
    campaign_name VARCHAR(255),
    country_code  VARCHAR(50),
    PRIMARY KEY (campaign_name, country_code),
    FOREIGN KEY (campaign_name) REFERENCES campaign (name),
    FOREIGN KEY (country_code) REFERENCES country (country_code)
);

CREATE TABLE campaign_has_item
(
    campaign_name VARCHAR(255),
    item_name     VARCHAR(255),
    PRIMARY KEY (campaign_name, item_name),
    FOREIGN KEY (campaign_name) REFERENCES campaign (name),
    FOREIGN KEY (item_name) REFERENCES item (item_name)
);


CREATE TABLE campaign_does_not_have_item
(
    campaign_name VARCHAR(255),
    item_name     VARCHAR(255),
    PRIMARY KEY (campaign_name, item_name),
    FOREIGN KEY (campaign_name) REFERENCES campaign (name),
    FOREIGN KEY (item_name) REFERENCES item (item_name)
);

CREATE TABLE clan
(
    clan_id CHAR(36) PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
);

CREATE TABLE player
(
    player_id          CHAR(36) PRIMARY KEY,
    credential         VARCHAR(255) NOT NULL,
    created            TIMESTAMP    NOT NULL,
    modified           TIMESTAMP    NOT NULL,
    last_session       TIMESTAMP    NOT NULL,
    total_spent        INT          NOT NULL,
    total_refund       INT          NOT NULL,
    total_transactions INT          NOT NULL,
    last_purchase      TIMESTAMP    NOT NULL,
    level              INT          NOT NULL,
    xp                 INT          NOT NULL,
    total_playtime     INT          NOT NULL,
    country            VARCHAR(50)  NOT NULL,
    language           VARCHAR(50)  NOT NULL,
    birthdate          TIMESTAMP    NOT NULL,
    gender             VARCHAR(50)  NOT NULL,
    clan_id            CHAR(36),
    _customfield       TEXT,
    FOREIGN KEY (clan_id) REFERENCES clan (clan_id),
    FOREIGN KEY (country) REFERENCES country (country_code)
);

CREATE TABLE device
(
    id        SERIAL PRIMARY KEY,
    player_id CHAR(36)     NOT NULL,
    model     VARCHAR(255) NOT NULL,
    carrier   VARCHAR(255) NOT NULL,
    firmware  VARCHAR(255) NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player (player_id)
);

CREATE TABLE inventory
(
    player_id CHAR(36)     NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    quantity  INT          NOT NULL,
    PRIMARY KEY (player_id, item_name),
    FOREIGN KEY (player_id) REFERENCES player (player_id),
    FOREIGN KEY (item_name) REFERENCES item (item_name)
);

INSERT INTO item (item_name, type)
VALUES ('item_1', 'ITEM');
INSERT INTO item (item_name, type)
VALUES ('item_4', 'ITEM');
INSERT INTO item (item_name, type)
VALUES ('item_34', 'ITEM');
INSERT INTO item (item_name, type)
VALUES ('item_55', 'ITEM');
INSERT INTO item (item_name, type)
VALUES ('cash', 'CASH');
INSERT INTO item (item_name, type)
VALUES ('coins', 'COIN');

INSERT INTO country (country_code)
VALUES ('US'),
       ('RO'),
       ('CA');

INSERT INTO campaign (name, game, priority, start_date, end_date, enabled, last_updated)
VALUES ('mycampaign', 'mygame', 10.5, '2022-01-25 00:00:00', '2022-02-25 00:00:00', TRUE, '2021-07-13 11:46:58');

INSERT INTO campaign_level (campaign_name, min_level, max_level)
VALUES ('mycampaign', 1, 3);

INSERT INTO campaign_country (campaign_name, country_code)
VALUES ('mycampaign', 'US'),
       ('mycampaign', 'RO'),
       ('mycampaign', 'CA');

INSERT INTO campaign_has_item (campaign_name, item_name)
VALUES ('mycampaign', 'item_1');

INSERT INTO campaign_does_not_have_item (campaign_name, item_name)
VALUES ('mycampaign', 'item_4');


INSERT INTO clan (clan_id, name)
VALUES ('123456', 'Hello world clan');

INSERT INTO player (player_id, credential, created, modified, last_session, total_spent, total_refund,
                    total_transactions, last_purchase, level, xp, total_playtime, country, language, birthdate, gender,
                    clan_id, _customfield)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', 'apple_credential', '2021-01-10 13:37:17', '2021-01-23 13:37:17',
        '2021-01-23 13:37:17', 400, 0, 5, '2021-01-22 13:37:17', 3, 1000, 144, 'CA', 'fr', '2000-01-10 13:37:17',
        'male', '123456', 'mycustom');

INSERT INTO device (player_id, model, carrier, firmware)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', 'apple iphone 11', 'vodafone', '123');

INSERT INTO inventory (player_id, item_name, quantity)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', 'cash', 123),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 'coins', 123),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 'item_1', 1),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 'item_34', 3),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 'item_55', 2);

ALTER TABLE player
    ADD COLUMN active_campaign VARCHAR(255);

CREATE TABLE player_clan (
                             player_id CHAR(36) NOT NULL,
                             clan_id CHAR(36) NOT NULL,
                             PRIMARY KEY (player_id, clan_id),
                             FOREIGN KEY (player_id) REFERENCES player(player_id),
                             FOREIGN KEY (clan_id) REFERENCES clan(clan_id)
);

CREATE TABLE player_device (
                               player_id CHAR(36) NOT NULL,
                               device_id INT NOT NULL,
                               PRIMARY KEY (player_id, device_id),
                               FOREIGN KEY (player_id) REFERENCES player(player_id),
                               FOREIGN KEY (device_id) REFERENCES device(id)
);

INSERT INTO player_device (player_id, device_id)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', 1);

INSERT INTO player_clan (player_id, clan_id)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', '123456');

INSERT INTO player (player_id, credential, created, modified, last_session, total_spent, total_refund,
                    total_transactions, last_purchase, level, xp, total_playtime, country, language, birthdate, gender,
                    _customfield)
VALUES
    ('97983be2-98b7-11e7-90cf-82playerid2', 'credential_2', '2021-02-11 14:00:00', '2021-02-24 14:00:00', '2021-02-24 14:00:00', 300, 0, 3, '2021-02-23 14:00:00', 2, 800, 120, 'US', 'en', '1995-06-15 00:00:00', 'female', 'custom_data_2'),
    ('p97983be2-98b7-11e7-90cf-82playerid3', 'credential_3', '2021-03-12 15:00:00', '2021-03-25 15:00:00', '2021-03-25 15:00:00', 500, 0, 7, '2021-03-24 15:00:00', 4, 1200, 180, 'US', 'en', '1998-07-20 00:00:00', 'male', 'custom_data_3');

INSERT INTO campaign (name, game, priority, start_date, end_date, enabled, last_updated)
VALUES
    ('campaign_2', 'game_2', 9.5, '2024-01-20 00:00:00', '2024-02-20 00:00:00', TRUE, now()),
    ('campaign_3', 'game_3', 8.5, '2024-02-15 00:00:00', '2024-03-15 00:00:00', TRUE, now());

INSERT INTO device (player_id, model, carrier, firmware)
VALUES
    ('97983be2-98b7-11e7-90cf-82playerid2','model_2', 'carrier_2', 'firmware_2'),
    ('97983be2-98b7-11e7-90cf-82playerid2','model_3', 'carrier_3', 'firmware_3');

INSERT INTO device (player_id, model, carrier, firmware)
VALUES
    ('p97983be2-98b7-11e7-90cf-82playerid3','model_2', 'carrier_2', 'firmware_2'),
    ('p97983be2-98b7-11e7-90cf-82playerid3','model_3', 'carrier_3', 'firmware_3');

INSERT INTO player_device (player_id, device_id)
VALUES
    ('97983be2-98b7-11e7-90cf-82playerid2', 2),
    ('p97983be2-98b7-11e7-90cf-82playerid3', 3);

INSERT INTO player_clan (player_id, clan_id)
VALUES
    ('97983be2-98b7-11e7-90cf-82playerid2', '123456'),
    ('p97983be2-98b7-11e7-90cf-82playerid3', '123456');

ALTER TABLE player
    ALTER COLUMN total_spent TYPE DECIMAL(10, 2)  ,
    ALTER COLUMN total_refund TYPE DECIMAL(10, 2);