package com.travel.flight_booking.infrastructure.kafka.consumers;

import com.travel.orchestrator.avro.TripSagaCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TripSagaKafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger(TripSagaKafkaConsumer.class);

	@KafkaListener(
		topics = "trip-saga-created",
		groupId = "orchestrator-service",
		containerFactory = "kafkaListenerContainerFactory")
	void consume(TripSagaCreatedEvent event, Acknowledgment ack) {
		try {
			logger.info("Received event: {}", event);
			ack.acknowledge();
		} catch (Exception error) {
			logger.error("Error while processing event: {}", event);
		}
	}
}
