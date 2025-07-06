package com.travel.flight_booking.infrastructure.kafka.consumers;

import com.travel.flight_booking.application.usecases.ProcessBookFlightCommand;
import com.travel.orchestrator.avro.BookFlightCommand;
import com.travel.flight_booking.domain.entities.Reservation;
import com.travel.flight_booking.infrastructure.kafka.producers.FlightBookedResponseProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class BookFlightCommandConsumer {
	private static final Logger logger = LoggerFactory.getLogger(BookFlightCommandConsumer.class);
	@Autowired
	private ProcessBookFlightCommand processBookFlightCommand;
	@Autowired
	private FlightBookedResponseProducer flightBookedResponseProducer;

	@KafkaListener(
		topics = "create-flight",
		groupId = "flight-booking-service",
		containerFactory = "kafkaListenerContainerFactory"
	)
	public void consume(BookFlightCommand event, Acknowledgment ack) {
		try {
			logger.info("Received event: {}", event);
			Reservation reservation = processBookFlightCommand.execute(event);
			flightBookedResponseProducer.send(event, reservation);
			ack.acknowledge();
		} catch (Exception error) {
			logger.error("Error while processing event: {}", event);
		}
	}
}
