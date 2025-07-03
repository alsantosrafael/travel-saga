package com.travel.flight_booking.application.services;

import com.travel.flight_booking.application.usecases.CreateFlight;
import com.travel.flight_booking.domain.entities.Flight;
import com.travel.flight_booking.domain.valueobjects.CreateFlightRequest;
import com.travel.flight_booking.domain.valueobjects.FlightResponse;
import com.travel.flight_booking.infrastructure.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

	@Autowired
	FlightRepository repository;

	@Autowired
	CreateFlight createFlight;

	public List<FlightResponse> fetch() {
		return repository
			.findAll()
			.stream()
			.map(FlightResponse::fromFlight)
			.collect(Collectors.toList());
	}

	public void create(CreateFlightRequest request) {
		createFlight.execute(request);
	}

}
