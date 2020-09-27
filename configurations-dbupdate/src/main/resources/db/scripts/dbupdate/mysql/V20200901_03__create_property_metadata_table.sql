DROP PROCEDURE IF EXISTS createPropertyMetadata;

DELIMITER //

CREATE PROCEDURE createPropertyMetadata()
BEGIN

    IF NOT EXISTS(SELECT *
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE LOWER(TABLE_NAME) = LOWER('property_metadata')
                    AND TABLE_SCHEMA = DATABASE())
    THEN
        CREATE TABLE IF NOT EXISTS property_metadata
        (
            id                 BINARY(16)   NOT NULL PRIMARY KEY,
            config_metadata_id VARCHAR(255) NOT NULL,
            name               VARCHAR(255) NOT NULL,
            property_group     VARCHAR(255) NOT NULL,
            type               VARCHAR(255) NOT NULL,
            default_value      VARCHAR(255),
            possible_values    TEXT,
            create_date        DATETIME     NOT NULL,
            update_date        DATETIME
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;

        ALTER TABLE property_metadata
            ADD CONSTRAINT FK_PROPERTY_CONFIG_METADATA
                FOREIGN KEY (config_metadata_id)
                    REFERENCES config_metadata(id);

        CREATE INDEX CREATE_DATE ON property_metadata (CREATE_DATE);
        CREATE INDEX UPDATE_DATE ON property_metadata (UPDATE_DATE);
    END IF;

END //
DELIMITER;

CALL createPropertyMetadata();

DROP PROCEDURE createPropertyMetadata;

