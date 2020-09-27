package com.immortal.configurations.api.jms;

import java.util.Collections;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.JmsJsonMessage;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.CONFIG_METADATA, action = JmsActions.UPDATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigMetadataUpdatedEvent extends JmsJsonMessage<ConfigMetadataEventDto>
{
    public ConfigMetadataUpdatedEvent()
    {
    }

    public ConfigMetadataUpdatedEvent(final ConfigMetadataEventDto jmsDto, final ConfigMetadataEventDto oldJmsDto)
    {
        this.setObjects(Collections.singletonList(jmsDto));
        this.setOldObjects(Collections.singletonList(oldJmsDto));
    }
}