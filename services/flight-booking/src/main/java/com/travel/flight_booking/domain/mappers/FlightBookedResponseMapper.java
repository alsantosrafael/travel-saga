package com.travel.flight_booking.domain.mappers;

import com.travel.orchestrator.avro.BookFlightCommand;
import com.travel.flight_booking.avro.FlightBookedResponse;
import com.travel.flight_booking.domain.entities.Reservation;
import com.travel.flight_booking.domain.enums.StepStatus;
import org.springframework.stereotype.Component;

@Component
public class FlightBookedResponseMapper {

	public FlightBookedResponse toOrchestratorEventResponse(BookFlightCommand event, Reservation reservation,
															StepStatus status, String message) {
		return FlightBookedResponse.newBuilder()
			.setUserId(event.getUserId())
			.setSagaId(event.getSagaId())
			.setFlightReservationId(reservation != null ? reservation.getId().toString() : "")
			.setStatus(String.valueOf(status))
			.setMessage(message)
			.build();
	}
}
