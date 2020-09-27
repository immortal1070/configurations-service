package com.immortal.configurations.entity;

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

;

@Entity(name = ConfigInstanceEntity.ENTITY_NAME)
@Table(name = ConfigInstanceEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ConfigInstanceEntity implements Serializable {
    public static final String ENTITY_NAME = "ConfigInstance";
    public static final String TABLE_NAME = "config_instance";

    @Id
    @Column(name = ID_COLUMN)
    @GenericGenerator(name = UUID_GENERATOR_TYPE, strategy = UUID_GENERATOR_TYPE)
    @GeneratedValue(generator = UUID_GENERATOR_TYPE)
    private UUID id;

    @Column(name = "create_date", nullable = false, updatable = false)
    private ZonedDateTime createDate;

    @Column(name = "update_date", insertable = false)
    private ZonedDateTime updateDate;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "config_metadata_id", nullable = false, updatable = false)
    private ConfigMetadataEntity configMetadata;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = ID_COLUMN, nullable = false, insertable = false, updatable = false)
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

    public List<PropertyValueEntity> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(final List<PropertyValueEntity> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
