package com.immortal.configurations.api.jms;

import java.util.Collections;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.PROPERTY_VALUE, action = JmsActions.DELETED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyValueDeletedEvent extends JmsJsonMessage<PropertyValueEventDto>
{
    public PropertyValueDeletedEvent()
    {
    }

    public PropertyValueDeletedEvent(final PropertyValueEventDto jmsDto)
    {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}