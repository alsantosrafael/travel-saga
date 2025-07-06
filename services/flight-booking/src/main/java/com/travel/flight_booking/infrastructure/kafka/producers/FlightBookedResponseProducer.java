package com.travel.flight_booking.infrastructure.kafka.producers;

import com.travel.flight_booking.domain.enums.StepStatus;
import com.travel.orchestrator.avro.BookFlightCommand;
import com.travel.flight_booking.avro.FlightBookedResponse;
import com.travel.flight_booking.domain.mappers.FlightBookedResponseMapper;
import com.travel.flight_booking.domain.entities.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FlightBookedResponseProducer {
	private static final Logger logger = LoggerFactory.getLogger(FlightBookedResponseProducer.class);

	@Autowired
	private KafkaTemplate<String, FlightBookedResponse> kafkaTemplate;
	@Autowired
	private FlightBookedResponseMapper flightBookedResponseMapper;
	private final String topic = "create-flight-response";

	public void send(BookFlightCommand receivedEvent, Reservation reservation, StepStatus status, String msg) {
		try {
			FlightBookedResponse message = flightBookedResponseMapper.toOrchestratorEventResponse(receivedEvent, reservation, status, msg);
			logger.info("Flight reservation with status={}, sending response to orchestrator event={}", status.toString(), receivedEvent);
			kafkaTemplate.send(topic, message.getSagaId(), message)
				.whenComplete(
					(result, ex) -> {
						if (ex == null) { logger.info("Message sent successfully to topic {} with key {}", topic, message.getSagaId()); }
						else { logger.error("Failed to send message to topic {} with key {}: {}", topic, message.getSagaId(), ex.getMessage());
						}
					});
		} catch (Exception e) {
			logger.error("Deu pau: {}", e.getMessage());
			throw e;
		}
	}
}
