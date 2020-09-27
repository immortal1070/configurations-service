package com.immortal.configurations.api.jms;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

import java.util.Collections;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.PROPERTY_METADATA, action = JmsActions.CREATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMetadataCreatedEvent extends JmsJsonMessage<PropertyMetadataEventDto> {
    public PropertyMetadataCreatedEvent() {
    }

    public PropertyMetadataCreatedEvent(final PropertyMetadataEventDto jmsDto) {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}
