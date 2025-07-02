package com.travel.flight_booking.infrastructure.repositories;

import com.travel.flight_booking.domain.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
