package com.travel.flight_booking.domain.valueobjects;

import java.time.LocalDateTime;

public record CreateFlightRequest(
	String flightCode,
	String originCode,
	String destinationCode,
	Integer capacity,
	LocalDateTime departureTime,
	LocalDateTime arrivalTime
) {
}
