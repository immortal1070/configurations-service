package com.immortal.configurations.api.jms;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

import java.util.Collections;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.PROPERTY_VALUE, action = JmsActions.CREATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyValueCreatedEvent extends JmsJsonMessage<PropertyValueEventDto> {
    public PropertyValueCreatedEvent() {
    }

    public PropertyValueCreatedEvent(final PropertyValueEventDto jmsDto) {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}
