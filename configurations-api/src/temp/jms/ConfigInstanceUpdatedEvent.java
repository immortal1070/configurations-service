package com.immortal.configurations.api.jms;

import com.immortal.event.ExternalEventDestination;
import com.immortal.jms.json.JmsJsonMessage;
import com.immortal.jms.json.constants.JmsActions;
import com.immortal.jms.json.constants.JmsVersions;

import java.util.Collections;

@ExternalEventDestination(topic = ConfigurationsConstants.Jms.Topics.configurations, versions = JmsVersions.VERSION_V1, subject = ConfigurationsConstants.Jms.Subjects.CONFIG_INSTANCE, action = JmsActions.UPDATED)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigInstanceUpdatedEvent extends JmsJsonMessage<ConfigInstanceEventDto> {
    public ConfigInstanceUpdatedEvent() {
    }

    public ConfigInstanceUpdatedEvent(final ConfigInstanceEventDto jmsDto, final ConfigInstanceEventDto oldJmsDto) {
        this.setObjects(Collections.singletonList(jmsDto));
        this.setOldObjects(Collections.singletonList(oldJmsDto));
    }
}
