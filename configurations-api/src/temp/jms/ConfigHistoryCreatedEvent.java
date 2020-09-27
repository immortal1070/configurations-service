package com.immortal.configurations.api.jms;

import com.immortal.configurations.api.constants.ConfigurationsConstants;
import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.JmsJsonMessage;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

import java.util.Collections;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.CONFIG_HISTORY, action = JmsActions.CREATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigHistoryCreatedEvent extends JmsJsonMessage<ConfigHistoryEventDto> {
    public ConfigHistoryCreatedEvent() {
    }

    public ConfigHistoryCreatedEvent(final ConfigHistoryEventDto jmsDto) {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}
