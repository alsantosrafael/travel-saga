package com.travel.flight_booking.domain.entities;

import com.travel.flight_booking.domain.enums.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "flight_id")
	private Flight flight;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "passenger_id")
	private Passenger passenger;

	@Column(nullable = false)
	private LocalDateTime reservationTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservationStatus status = ReservationStatus.ACTIVE;

	@Column
	private String seatNumber;


	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
