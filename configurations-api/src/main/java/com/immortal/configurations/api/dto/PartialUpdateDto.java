package com.immortal.configurations.api.dto;

import java.util.Set;

/**
 * Interface for dto with partial update functionality
 */
public interface PartialUpdateDto
{
    Set<String> getReceivedFields();
}
