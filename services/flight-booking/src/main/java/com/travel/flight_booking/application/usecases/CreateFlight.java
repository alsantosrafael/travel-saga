package com.travel.flight_booking.application.usecases;

import com.travel.flight_booking.domain.valueobjects.CreateFlightRequest;
import com.travel.flight_booking.infrastructure.repositories.AirportRepository;
import com.travel.flight_booking.infrastructure.repositories.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CreateFlight {
	private static final Logger logger = LoggerFactory.getLogger(CreateFlight.class);
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private AirportRepository airportRepository;

	@Value("${airport.db.api.url}")
	String airportDbUrl;

	@Value("${airport.db.api.token}")
	String airportDbToken;


	public void execute(CreateFlightRequest request) {
		// TODO: Before creating a new flight, look for the airports using its destinationCode and originCode
		// TODO: Maybe try to call an external API to get more details
		// TODO: Save the airport to our database if not exists
		// TODO: Create the flight if not exists
		try {
			callAirportDb(request.destinationCode());
		} catch (Exception e) {
			logger.error("Error while calling Airport DB API", e);
		}

	}

	private void callAirportDb(String code) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		URI myURl = URI.create(airportDbUrl + "/" + code + "?apiToken=" + airportDbToken);
		HttpRequest request = HttpRequest.newBuilder()
			.uri(myURl)
			.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		logger.info(response.body());
	}
}
