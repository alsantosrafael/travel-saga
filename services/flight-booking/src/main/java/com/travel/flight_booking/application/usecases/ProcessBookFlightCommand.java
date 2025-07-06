package com.travel.flight_booking.application.usecases;

import com.travel.flight_booking.domain.entities.Flight;
import com.travel.flight_booking.domain.entities.Passenger;
import com.travel.flight_booking.domain.entities.Reservation;
import com.travel.flight_booking.infrastructure.repositories.FlightRepository;
import com.travel.flight_booking.infrastructure.repositories.PassengerRepository;
import com.travel.flight_booking.infrastructure.repositories.ReservationRepository;
import com.travel.orchestrator.avro.BookFlightCommand;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProcessBookFlightCommand {
	private static final Logger logger = LoggerFactory.getLogger(ProcessBookFlightCommand.class);

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Transactional
	public Reservation execute(BookFlightCommand event) {
		logger.info("Starting processing event for saga=#{} and user={}", event.getSagaId(), event.getUserId());
		logger.info("Creating flight for saga=#{} and user={}", event.getSagaId(), event.getUserId());
		Flight flight = fetchFlight(event.getFlightDetails().getFlightCode(),
			event.getFlightDetails().getOriginCode(),
			event.getFlightDetails().getDestinationCode());
		logger.info("Creating reservation for saga=#{} and user={}", event.getSagaId(), event.getUserId());
		Reservation reservation = makeReservation(flight, event);
		flight.addReservation(reservation);
		 flightRepository.save(flight);
		logger.info("Created reservation successfully for saga=#{} and user={}", event.getSagaId(), event.getUserId());
		return reservation;
	}

	private Flight fetchFlight(String flightCode, String originCode, String destinationCode) {
		return flightRepository.findByFlightCodeAndOrigin_CodeAndDestination_Code(
			flightCode,
			originCode,
			destinationCode
		).orElseThrow(() -> new RuntimeException("Flight not found"));
	}

	private Reservation makeReservation(Flight flight, BookFlightCommand event) {
		Passenger passenger = createOrFetchPassenger(event);
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setSeatNumber(event.getFlightDetails().getSeatNumber());
		return reservationRepository.save(reservation);
	}

	private Passenger createOrFetchPassenger(BookFlightCommand event) {
		UUID userId = UUID.fromString(event.getUserId());
		return passengerRepository.findByIdAndDocumentAndEmail(
			userId,
			event.getDocument(),
			event.getEmail()
		).orElseGet(() -> {
			Passenger passenger = new Passenger();
			passenger.setDocument(event.getDocument());
			passenger.setEmail(event.getEmail());
			return passengerRepository.save(passenger);
		});
	}
}
