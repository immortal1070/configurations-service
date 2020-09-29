DROP PROCEDURE IF EXISTS createPropertyMetadataTags;

DELIMITER //

CREATE PROCEDURE createPropertyMetadataTags()
BEGIN

    IF NOT EXISTS(SELECT *
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE LOWER(TABLE_NAME) = LOWER('property_metadata_tags')
                    AND TABLE_SCHEMA = DATABASE())
    THEN
        CREATE TABLE IF NOT EXISTS property_metadata_tags
        (
            property_metadata_id BINARY(16)   NOT NULL,
            tag                  VARCHAR(255) NOT NULL
        ) ENGINE = InnoDB
          DEFAULT CHARSET = utf8;


        ALTER TABLE property_metadata_tags
            ADD CONSTRAINT FK_PROPERTY_METADATA_TAGS_TO_PROPERTY
                FOREIGN KEY (property_metadata_id)
                    REFERENCES property_metadata (id) ON DELETE CASCADE;

        CREATE INDEX PROPERTY_METADATA_ID ON property_metadata_tags (property_metadata_id);
        CREATE INDEX TAG ON property_metadata_tags (tag);
    END IF;

END //
DELIMITER;

CALL createPropertyMetadataTags();

DROP PROCEDURE createPropertyMetadataTags;

