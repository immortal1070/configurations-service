package com.immortal.configurations.transformer;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.immortal.configurations.api.dto.PartialUpdateDto;

/**
 * Can be filled with setters for partial parameters and then provides modifier which updates entity.
 *
 * e.g.:
 * put(NAME, (entity, dto) -> entity.setName(dto.getName()));
 * put(IS_ACTIVE, (entity, dto) -> entity.setIsActive(dto.getIsActive()));
 *
 * dtoToModifier(dto, partial);
 *
 * @param <E> - entity
 * @param <D> - dto
 */
public class ModifiersMap<E, D extends PartialUpdateDto> extends HashMap<String, BiConsumer<E, D>>
{
    /**
     * Provides modifier which updates entity based on "received fields" and partial parameter
     * @param dto - dto from which entity should be updated
     * @param partial - if true, then it partial update, if false - then all fields should be updated
     * @return
     */
    public Consumer<E> dtoToModifier(final D dto, final boolean partial)
    {
        if (dto == null)
        {
            return (entity) -> {
            };
        }

        return (entity) -> this.forEach((fieldName, setter) -> {
            if (!partial || dto.getReceivedFields().contains(fieldName))
            {
                setter.accept(entity, dto);
            }
        });
    }
}
