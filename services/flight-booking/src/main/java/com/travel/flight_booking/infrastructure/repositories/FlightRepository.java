package com.travel.flight_booking.infrastructure.repositories;

import com.travel.flight_booking.domain.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	Optional<Flight> findByFlightCodeAndOrigin_CodeAndDestination_Code(
		String flightCode, String originCode, String destinationCode);
}
