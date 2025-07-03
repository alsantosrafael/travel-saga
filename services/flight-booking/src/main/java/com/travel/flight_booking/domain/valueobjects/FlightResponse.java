package com.travel.flight_booking.domain.valueobjects;

import com.travel.flight_booking.domain.entities.Airport;
import com.travel.flight_booking.domain.entities.Flight;
import com.travel.flight_booking.domain.entities.Reservation;
import com.travel.flight_booking.domain.enums.FlightStatus;

import java.time.LocalDateTime;
import java.util.Set;

public record FlightResponse (
	Long id,
	String flightCode,
	Airport origin,
	Airport destination,
	int capacity,
	LocalDateTime departureTime,
	LocalDateTime arrivalTime,
	FlightStatus flightStatus,
	Set<Reservation> reservations,
	boolean isFullyBooked
) {
	public static FlightResponse fromFlight(Flight flight) {
		return new FlightResponse(
			flight.getId(),
			flight.getFlightCode(),
			flight.getOrigin(),
			flight.getDestination(),
			flight.getCapacity(),
			flight.getDepartureTime(),
			flight.getArrivalTime(),
			flight.getFlightStatus(),
			flight.getReservations(),
			flight.getCapacity() == flight.getReservations().size()
		);
	}
}
