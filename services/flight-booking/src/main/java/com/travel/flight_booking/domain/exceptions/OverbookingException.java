package com.travel.flight_booking.domain.exceptions;

public class OverbookingException extends RuntimeException {
	public OverbookingException() {
		super("This flight has already reached its full capacity");
	}
}
