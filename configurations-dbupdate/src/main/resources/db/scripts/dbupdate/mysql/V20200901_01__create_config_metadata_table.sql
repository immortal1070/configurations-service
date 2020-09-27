DROP PROCEDURE IF EXISTS createConfigMetadata;

DELIMITER //

CREATE PROCEDURE createConfigMetadata()
BEGIN

    IF NOT EXISTS(SELECT *
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE LOWER(TABLE_NAME) = LOWER('config_metadata')
                    AND TABLE_SCHEMA = DATABASE())
    THEN
        CREATE TABLE IF NOT EXISTS config_metadata
        (
            id          VARCHAR(255) NOT NULL PRIMARY KEY,
            create_date DATETIME     NOT NULL,
            update_date DATETIME
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;

        CREATE INDEX CREATE_DATE ON config_metadata (CREATE_DATE);
        CREATE INDEX UPDATE_DATE ON config_metadata (UPDATE_DATE);
    END IF;

END //
DELIMITER;

CALL createConfigMetadata();

DROP PROCEDURE createConfigMetadata;

