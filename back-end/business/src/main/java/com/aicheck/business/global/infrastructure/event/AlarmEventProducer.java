package com.aicheck.business.global.infrastructure.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.aicheck.business.global.infrastructure.event.dto.request.AlarmEventMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlarmEventProducer {

	private static final String TOPIC = "alarm";
	private final KafkaTemplate<String, AlarmEventMessage> kafkaTemplate;

	public void sendEvent(AlarmEventMessage message) {
		kafkaTemplate.send(TOPIC, message);
	}
}
