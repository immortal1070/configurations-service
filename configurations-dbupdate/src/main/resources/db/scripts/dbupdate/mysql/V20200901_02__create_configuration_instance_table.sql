DROP PROCEDURE IF EXISTS createConfigInstance;

DELIMITER //

CREATE PROCEDURE createConfigInstance()
BEGIN

    IF NOT EXISTS(SELECT *
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE LOWER(TABLE_NAME) = LOWER('config_instance')
                    AND TABLE_SCHEMA = DATABASE())
    THEN
        CREATE TABLE IF NOT EXISTS config_instance
        (
            id                 BINARY(16)   NOT NULL PRIMARY KEY,
            name               VARCHAR(255) NOT NULL,
            config_metadata_id VARCHAR(255) NOT NULL,
            create_date        DATETIME     NOT NULL,
            update_date        DATETIME
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;

        ALTER TABLE config_instance
            ADD CONSTRAINT FK_CONFIG_METADATA
                FOREIGN KEY (config_metadata_id)
                    REFERENCES config_metadata(id);

        CREATE INDEX CREATE_DATE ON config_instance (CREATE_DATE);
        CREATE INDEX UPDATE_DATE ON config_instance (UPDATE_DATE);
    END IF;

END //
DELIMITER;

CALL createConfigInstance();

DROP PROCEDURE createConfigInstance;

