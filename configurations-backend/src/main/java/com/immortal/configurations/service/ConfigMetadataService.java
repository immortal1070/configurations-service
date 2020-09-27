package com.immortal.configurations.service;

import com.immortal.configurations.api.dto.ConfigMetadataDto;
import com.immortal.configurations.api.dto.ConfigMetadataPersistDto;
import com.immortal.configurations.api.params.PropertyMetadataSearchParams;
import com.immortal.configurations.dao.ConfigMetadataDao;
import com.immortal.configurations.entity.ConfigMetadataEntity;
import com.immortal.configurations.interceptors.Logged;
import com.immortal.configurations.transformer.ConfigMetadataTransformer;
import com.immortal.configurations.validation.ExistConfigMetadata;
import com.immortal.configurations.validation.NotExistConfigMetadata;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static com.immortal.configurations.constants.PersistenceConstants.PERSISTENCE_CONTEXT;

@Logged
@Transactional
public class ConfigMetadataService {
    @Inject
    private PropertyMetadataService propertyMetadataService;

    @Inject
    private ConfigMetadataDao dao;

    @Inject
    private ConfigMetadataTransformer transformer;

    @PersistenceContext(unitName = PERSISTENCE_CONTEXT)
    private EntityManager em;

    public boolean exists(final String id) {
        return dao.exists(id);
    }

    public ConfigMetadataDto findById(final String id) {
        return transformer.entityToDto(dao.findById(id));
    }

    public List<ConfigMetadataDto> find() {
        return transformer.entitiesToDtos(dao.findAll());
    }

    public ConfigMetadataDto create(@NotExistConfigMetadata final String id) {
        return transformer.entityToDto(dao.create(new ConfigMetadataEntity(id)));
    }

    public void delete(@ExistConfigMetadata final String id) {
        propertyMetadataService.delete(new PropertyMetadataSearchParams().setConfigMetadataIds(
            Collections.singletonList(id)
        ));
        dao.deleteById(id);
    }

    public ConfigMetadataDto register(final ConfigMetadataPersistDto configMetadataPersistDto) {
        String configMetadataId = configMetadataPersistDto.getId();
        ConfigMetadataDto configMetadataDto = findById(configMetadataId);
        if (configMetadataDto == null) {
            configMetadataDto = create(configMetadataId);
        }
        final ConfigMetadataDto finalConfigMetadataDto = configMetadataDto;
        CollectionUtils.emptyIfNull(configMetadataPersistDto.getPropertyMetadataGroups())
            .forEach(metadataGroup -> propertyMetadataService
                .registerGroup(finalConfigMetadataDto.getId(), metadataGroup));

        return transformer.entityToDto(dao.findById(configMetadataDto.getId()));
    }
}
