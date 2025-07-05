package com.travel.flight_booking.infrastructure.repositories;

import com.travel.flight_booking.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
