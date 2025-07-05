package com.travel.flight_booking.infrastructure.repositories;

import com.travel.flight_booking.domain.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	Optional<Passenger> findByIdAndDocumentAndEmail(UUID id, String document, String email);
}
