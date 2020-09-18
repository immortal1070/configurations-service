DROP PROCEDURE IF EXISTS dbupdateProcedure;

DELIMITER //

CREATE PROCEDURE dbupdateProcedure()
BEGIN

    IF NOT EXISTS(SELECT *
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE LOWER(TABLE_NAME) = LOWER('property_metadata_tags')
                    AND TABLE_SCHEMA = DATABASE())
    THEN
        CREATE TABLE IF NOT EXISTS property_metadata_tags
        (
            property_metadata_id BINARY(16) NOT NULL PRIMARY KEY,
            tag                  VARCHAR(255)
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;
    END IF;

END //
DELIMITER;

CALL dbupdateProcedure();

DROP PROCEDURE dbupdateProcedure;

