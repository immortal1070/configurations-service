package com.immortal.configurations.entity;

import static com.immortal.configurations.constants.PersistenceConstants.ID_COLUMN;
import static com.immortal.configurations.constants.PersistenceConstants.UUID_GENERATOR_TYPE;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.immortal.configurations.util.DateUtil;

@Entity(name = PropertyValueEntity.ENTITY_NAME)
@Table(name = PropertyValueEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class PropertyValueEntity implements Serializable
{
    public static final String ENTITY_NAME = "PropertyValue";
    public static final String TABLE_NAME = "property_value";

    @Id
    @Column(name = ID_COLUMN)
    @GenericGenerator(name = UUID_GENERATOR_TYPE, strategy = UUID_GENERATOR_TYPE)
    @GeneratedValue(generator = UUID_GENERATOR_TYPE)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "config_instance_id", nullable = false, updatable = false)
    private ConfigInstanceEntity configInstance;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "create_date", nullable = false, updatable = false)
    private ZonedDateTime createDate;

    @Column(name = "update_date", insertable = false)
    private ZonedDateTime updateDate;

    public PropertyValueEntity() {
    }

    @PrePersist
    public void prePersist()
    {
        this.createDate = DateUtil.getZonedNowInUtc();
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updateDate = DateUtil.getZonedNowInUtc();
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
        return createDate;
    }

    public void setCreateDate(final ZonedDateTime createDate)
    {
        this.createDate = createDate;
    }

    public ZonedDateTime getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(final ZonedDateTime updateDate)
    {
        this.updateDate = updateDate;
    }

    public ConfigInstanceEntity getConfigurationInstance()
    {
        return configInstance;
    }

    public void setConfigurationInstance(final ConfigInstanceEntity configInstance)
    {
        this.configInstance = configInstance;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }
}
