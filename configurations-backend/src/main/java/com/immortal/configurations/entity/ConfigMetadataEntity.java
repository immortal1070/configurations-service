package com.immortal.configurations.entity;

import static com.immortal.configurations.constants.PersistenceConstants.ID_COLUMN;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.immortal.configurations.util.DateUtil;

@Entity(name = ConfigMetadataEntity.ENTITY_NAME)
@Table(name = ConfigMetadataEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ConfigMetadataEntity implements Serializable
{
    public static final String ENTITY_NAME = "ConfigMetadata";
    public static final String TABLE_NAME = "config_metadata";
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = ID_COLUMN, nullable = false, updatable = false)
    private String id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = ID_COLUMN, nullable = false, insertable = false, updatable = false)
    private List<PropertyMetadataEntity> propertyMetadatas;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Column(name = "update_date", insertable = false)
    private Date updateDate;

    public ConfigMetadataEntity()
    {
    }

    public ConfigMetadataEntity(final String id)
    {
        this.id = id;
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

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
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

    public List<PropertyMetadataEntity> getPropertyMetadatas()
    {
        return propertyMetadatas;
    }

    public void setPropertyMetadatas(final List<PropertyMetadataEntity> propertyMetadatas)
    {
        this.propertyMetadatas = propertyMetadatas;
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
