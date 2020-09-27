package com.immortal.configurations.api.jms;

import java.util.Collections;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.CONFIG_INSTANCE, action = JmsActions.CREATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigInstanceCreatedEvent extends JmsJsonMessage<ConfigInstanceEventDto>
{
    public ConfigInstanceCreatedEvent()
    {
    }

    public ConfigInstanceCreatedEvent(final ConfigInstanceEventDto jmsDto)
    {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}