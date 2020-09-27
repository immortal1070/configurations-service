package com.immortal.configurations.api.jms;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

import java.util.Collections;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.CONFIG_INSTANCE, action = JmsActions.DELETED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigInstanceDeletedEvent extends JmsJsonMessage<ConfigInstanceEventDto> {
    public ConfigInstanceDeletedEvent() {
    }

    public ConfigInstanceDeletedEvent(final ConfigInstanceEventDto jmsDto) {
        this.setObjects(Collections.singletonList(jmsDto));
    }
}
