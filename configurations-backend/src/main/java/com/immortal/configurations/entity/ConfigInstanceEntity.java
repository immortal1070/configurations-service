package com.immortal.configurations.entity;

import com.immortal.configurations.constants.PersistenceConstants;
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

@Entity(name = ConfigInstanceEntity.ENTITY_NAME)
@Table(name = ConfigInstanceEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ConfigInstanceEntity implements Serializable {
    public static final String ENTITY_NAME = "ConfigInstance";
    public static final String TABLE_NAME = "config_instance";

    interface Columns {
        String CONFIG_METADATA_ID = "config_metadata_id";
    }

    @Id
    @Column(name = ID_COLUMN)
    @GenericGenerator(name = UUID_GENERATOR_TYPE, strategy = UUID_GENERATOR_TYPE)
    @GeneratedValue(generator = UUID_GENERATOR_TYPE)
    private UUID id;

    @Column(name = PersistenceConstants.CREATE_DATE_COLUMN, nullable = false, updatable = false)
    private ZonedDateTime createDate;

    @Column(name = PersistenceConstants.UPDATE_DATE_COLUMN, insertable = false)
    private ZonedDateTime updateDate;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Columns.CONFIG_METADATA_ID, nullable = false)
    private ConfigMetadataEntity configMetadata;

    /**
     * for optimization, so you don't need to lazy load or join to config_metadata table when it's not needed.
     */
    @Column(name = Columns.CONFIG_METADATA_ID, insertable = false, updatable = false, nullable = false)
    private String configMetadataId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configInstance")
    private List<PropertyValueEntity> propertyValues;

    public ConfigInstanceEntity() {
    }

    @PrePersist
    public void prePersist() {
        this.createDate = DateUtil.getZonedNowInUtc();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = DateUtil.getZonedNowInUtc();
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public List<PropertyValueEntity> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(final List<PropertyValueEntity> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
