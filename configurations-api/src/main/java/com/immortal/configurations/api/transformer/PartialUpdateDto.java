package com.immortal.configurations.api.transformer;

import java.util.Set;

/**
 * Interface for dto with partial update functionality
 */
public interface PartialUpdateDto {
    Set<String> getReceivedFields();
}
