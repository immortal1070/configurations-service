package com.immortal.configurations.entity;

import com.immortal.configurations.constants.PersistenceConstants;
import com.immortal.configurations.util.DateUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.immortal.configurations.constants.PersistenceConstants.ID_COLUMN;
import static com.immortal.configurations.constants.PersistenceConstants.UUID_GENERATOR_TYPE;

@Entity(name = PropertyValueEntity.ENTITY_NAME)
@Table(name = PropertyValueEntity.TABLE_NAME)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class PropertyValueEntity implements Serializable {
    public static final String ENTITY_NAME = "PropertyValue";
    public static final String TABLE_NAME = "property_value";

    public interface Columns {
        String CONFIG_INSTANCE_ID = "config_instance_id";
    }

    @Id
    @Column(name = ID_COLUMN)
    @GenericGenerator(name = UUID_GENERATOR_TYPE, strategy = UUID_GENERATOR_TYPE)
    @GeneratedValue(generator = UUID_GENERATOR_TYPE)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Columns.CONFIG_INSTANCE_ID, nullable = false)
    private ConfigInstanceEntity configInstance;

    /**
     * for optimization, so you don't need to lazy load or join to config_metadata table when it's not needed.
     */
    @Column(name = Columns.CONFIG_INSTANCE_ID, insertable = false, updatable = false, nullable = false)
    private UUID configInstanceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = PersistenceConstants.CREATE_DATE_COLUMN, nullable = false, updatable = false)
    private ZonedDateTime createDate;

    @Column(name = PersistenceConstants.UPDATE_DATE_COLUMN, insertable = false)
    private ZonedDateTime updateDate;

    public PropertyValueEntity() {
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

    public ConfigInstanceEntity getConfigInstance() {
        return configInstance;
    }

    public void setConfigInstance(final ConfigInstanceEntity configInstance) {
        this.configInstance = configInstance;
    }

    public UUID getConfigInstanceId() {
        return configInstanceId;
    }

    public void setConfigInstanceId(final UUID configInstanceId) {
        this.configInstanceId = configInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
