DROP PROCEDURE IF EXISTS createPropertyValue;

DELIMITER //

CREATE PROCEDURE createPropertyValue()
BEGIN

    IF NOT EXISTS(SELECT *
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE LOWER(TABLE_NAME) = LOWER('property_value')
                    AND TABLE_SCHEMA = DATABASE())
    THEN
        CREATE TABLE IF NOT EXISTS property_value
        (
            id                 BINARY(16)   NOT NULL PRIMARY KEY,
            config_instance_id BINARY(16)   NOT NULL,
            name               VARCHAR(255) NOT NULL,
            value              VARCHAR(255),
            create_date        DATETIME     NOT NULL,
            update_date        DATETIME
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;

        ALTER TABLE property_value
            ADD CONSTRAINT FK_PROPERTY_VALUE_CONFIG_INSTANCE
                FOREIGN KEY (config_instance_id)
                    REFERENCES config_instance (id);

        CREATE UNIQUE INDEX PROPERTY_VALUE_UNIQUE ON property_value (config_instance_id, name);

        CREATE INDEX CREATE_DATE ON property_value (create_date);
        CREATE INDEX UPDATE_DATE ON property_value (update_date);
    END IF;

END //
DELIMITER;

CALL createPropertyValue();

DROP PROCEDURE createPropertyValue;

