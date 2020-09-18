package com.immortal.configurations.entity;

import static com.immortal.configurations.constants.PersistenceConstants.ID_COLUMN;
import static com.immortal.configurations.constants.PersistenceConstants.UUID_GENERATOR_TYPE;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.immortal.configurations.util.DateUtil;

;

@Entity(name = ConfigInstanceEntity.ENTITY_NAME)
@Table(name = ConfigInstanceEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ConfigInstanceEntity implements Serializable
{
    public static final String ENTITY_NAME = "ConfigInstance";
    public static final String TABLE_NAME = "config_instance";
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = ID_COLUMN)
    @GenericGenerator(name = UUID_GENERATOR_TYPE, strategy = UUID_GENERATOR_TYPE)
    @GeneratedValue(generator = UUID_GENERATOR_TYPE)
    private UUID id;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Column(name = "update_date", insertable = false)
    private Date updateDate;

    @Column(name = "name", nullable = false)
    private String name;

    @Transient
    //    @Column(name = "config_metadata", nullable = false, updatable = false)
    private ConfigMetadataEntity configMetadata;

    @Transient
//    @Column(name = "properties")
    private List<PropertyValueEntity> propertyValues;

    public ConfigInstanceEntity()
    {
    }

    @PrePersist
    public void prePersist()
    {
        this.createDate = DateUtil.getNowInUtc();
        setDefaultValues();
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updateDate = DateUtil.getNowInUtc();
        setDefaultValues();
    }

    private void setDefaultValues()
    {
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(final UUID id)
    {
        this.id = id;
    }

    public ZonedDateTime getCreateDate()
    {
        return DateUtil.convertToZoneDateTimeInUtc(createDate);
    }

    public void setCreateDate(final ZonedDateTime createDate)
    {
        this.createDate = DateUtil.convertToDateFromZoneDateTime(createDate);
    }

    public ZonedDateTime getUpdateDate()
    {
        return DateUtil.convertToZoneDateTimeInUtc(updateDate);
    }

    public void setUpdateDate(final ZonedDateTime updateDate)
    {
        this.updateDate = DateUtil.convertToDateFromZoneDateTime(updateDate);
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public ConfigMetadataEntity getConfigMetadata()
    {
        return configMetadata;
    }

    public void setConfigMetadata(final ConfigMetadataEntity configMetadata)
    {
        this.configMetadata = configMetadata;
    }

    public List<PropertyValueEntity> getPropertyValues()
    {
        return propertyValues;
    }

    public void setPropertyValues(final List<PropertyValueEntity> propertyValues)
    {
        this.propertyValues = propertyValues;
    }

    /**
     * Queries params names
     */
    public interface ParamsNames
    {
    }

    /**
     * Named queries names
     */
    public interface QueryNames
    {
    }

}
