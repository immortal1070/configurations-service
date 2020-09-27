package com.immortal.configurations.entity;

import com.immortal.configurations.util.DateUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import static com.immortal.configurations.constants.PersistenceConstants.ID_COLUMN;

@Entity(name = ConfigMetadataEntity.ENTITY_NAME)
@Table(name = ConfigMetadataEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ConfigMetadataEntity implements Serializable {
    public static final String ENTITY_NAME = "ConfigMetadata";
    public static final String TABLE_NAME = "config_metadata";

    @Id
    @Column(name = ID_COLUMN, nullable = false, updatable = false)
    private String id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configMetadata",
        orphanRemoval = true)
    private List<PropertyMetadataEntity> propertyMetadatas;

    @Column(name = "create_date", nullable = false, updatable = false)
    private ZonedDateTime createDate;

    @Column(name = "update_date", insertable = false)
    private ZonedDateTime updateDate;

    public ConfigMetadataEntity() {
    }

    public ConfigMetadataEntity(final String id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        this.createDate = DateUtil.getZonedNowInUtc();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = DateUtil.getZonedNowInUtc();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
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

    public List<PropertyMetadataEntity> getPropertyMetadatas() {
        return propertyMetadatas;
    }

    public void setPropertyMetadatas(final List<PropertyMetadataEntity> propertyMetadatas) {
        this.propertyMetadatas = propertyMetadatas;
    }
}
