package com.immortal.configurations.api.jms;

import java.util.Collections;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.PROPERTY_METADATA, action = JmsActions.DELETED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMetadataDeletedEvent extends JmsJsonMessage<PropertyMetadataEventDto>
{
    public PropertyMetadataDeletedEvent()
    {
    }

    public PropertyMetadataDeletedEvent(final PropertyMetadataEventDto jmsDto)
    {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}