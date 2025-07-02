package com.travel.flight_booking.infrastructure.http;

import com.travel.flight_booking.application.services.FlightService;
import com.travel.flight_booking.domain.valueobjects.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	FlightService flightService;

	@GetMapping
	ResponseEntity<List<FlightResponse>> fetchFlights() {
		return ResponseEntity.ok().body(flightService.fetch());
	}
}
