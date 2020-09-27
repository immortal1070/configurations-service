package com.immortal.configurations.entity;

import com.immortal.configurations.entity.converters.StringListConverter;
import com.immortal.configurations.util.DateUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.constants.PersistenceConstants.ID_COLUMN;
import static com.immortal.configurations.constants.PersistenceConstants.UUID_GENERATOR_TYPE;

@Entity(name = PropertyMetadataEntity.ENTITY_NAME)
@Table(name = PropertyMetadataEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@NamedEntityGraph(name = PropertyMetadataEntity.Graphs.TAGS,
    attributeNodes = @NamedAttributeNode("tags"))
public class PropertyMetadataEntity implements Serializable {
    public static final String ENTITY_NAME = "PropertyMetadata";
    public static final String TABLE_NAME = "property_metadata";

    public interface Columns {
        String CONFIG_METADATA_ID = "config_metadata_id";
    }

    public interface Graphs {
        String TAGS = "graph." + ENTITY_NAME + ".tags";
    }

    @Id
    @Column(name = ID_COLUMN)
    @GenericGenerator(name = UUID_GENERATOR_TYPE, strategy = UUID_GENERATOR_TYPE)
    @GeneratedValue(generator = UUID_GENERATOR_TYPE)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Columns.CONFIG_METADATA_ID)
    private ConfigMetadataEntity configMetadata;

    /**
     * for optimization, so you don't need to lazy load or join to config_metadata table when it's not needed.
     */
    @Column(name = Columns.CONFIG_METADATA_ID, insertable = false, updatable = false)
    private String configMetadataId;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "property_group", nullable = false)
    private String group;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "possible_values")
    @Convert(converter = StringListConverter.class)
    private List<String> possibleValues;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "property_metadata_tags", joinColumns = @JoinColumn(name = "property_metadata_id"))
    @Column(name = "tag")
    private List<String> tags;

    @Column(name = "create_date", nullable = false, updatable = false)
    private ZonedDateTime createDate;

    @Column(name = "update_date", insertable = false)
    private ZonedDateTime updateDate;

    public PropertyMetadataEntity() {
    }

    @PrePersist
    public void prePersist() {
        this.createDate = DateUtil.getZonedNowInUtc();
        setDefaultValues();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = DateUtil.getZonedNowInUtc();
        setDefaultValues();
    }

    private void setDefaultValues() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(final ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public ConfigMetadataEntity getConfigMetadata() {
        return configMetadata;
    }

    public void setConfigMetadata(final ConfigMetadataEntity configMetadata) {
        this.configMetadata = configMetadata;
    }

    public String getConfigMetadataId() {
        return configMetadataId;
    }

    public void setConfigMetadataId(final String configMetadataId) {
        this.configMetadataId = configMetadataId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(final List<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(final List<String> tags) {
        this.tags = tags;
    }
}
