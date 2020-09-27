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
            id                     BINARY(16) NOT NULL PRIMARY KEY,
            configuration_instance_id BINARY(16)   NOT NULL,
            name VARCHAR (255) NOT NULL,
            value                  VARCHAR(255),
            create_date            DATETIME   NOT NULL,
            update_date            DATETIME
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;

        CREATE INDEX CREATE_DATE ON property_value (CREATE_DATE);
        CREATE INDEX UPDATE_DATE ON property_value (UPDATE_DATE);
    END IF;

END //
DELIMITER;

CALL createPropertyValue();

DROP PROCEDURE createPropertyValue;

