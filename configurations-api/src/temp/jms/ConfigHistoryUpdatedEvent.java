package com.immortal.configurations.api.jms;

import java.util.Collections;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.JmsJsonMessage;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.CONFIG_HISTORY, action = JmsActions.UPDATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigHistoryUpdatedEvent extends JmsJsonMessage<ConfigHistoryEventDto>
{
    public ConfigHistoryUpdatedEvent()
    {
    }

    public ConfigHistoryUpdatedEvent(final ConfigHistoryEventDto jmsDto, final ConfigHistoryEventDto oldJmsDto)
    {
        this.setObjects(Collections.singletonList(jmsDto));
        this.setOldObjects(Collections.singletonList(oldJmsDto));
    }
}