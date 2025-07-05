package com.travel.flight_booking.infrastructure.kafka.producers;

import com.travel.flight_booking.avro.BookFlightCommand;
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
	private static FlightBookedResponseMapper flightBookedResponseMapper;
	private final String topic = "create-flight-response";

	public void send(BookFlightCommand receivedEvent, Reservation reservation) {
		FlightBookedResponse message = flightBookedResponseMapper.toOrchestratorEventSuccess(receivedEvent, reservation);
		logger.info("Flight reservation DONE, sending response to orchestrator event={}", receivedEvent);
		kafkaTemplate.send(topic, message.getSagaId(), message);
	}
}
