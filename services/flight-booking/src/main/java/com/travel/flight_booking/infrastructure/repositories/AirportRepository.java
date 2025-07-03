package com.travel.flight_booking.infrastructure.repositories;


import com.travel.flight_booking.domain.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
