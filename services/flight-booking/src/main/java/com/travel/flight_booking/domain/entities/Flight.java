package com.travel.flight_booking.domain.entities;

import com.travel.flight_booking.domain.enums.FlightStatus;
import com.travel.flight_booking.domain.exceptions.OverbookingException;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="FLIGHT")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String flightCode;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name="origin_id")
	private Airport origin;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name="destination_id")
	private Airport destination;

	@Column(nullable = false)
	private int capacity;

	@Column(nullable = false)
	private LocalDateTime departureTime;

	@Column(nullable = false)
	private LocalDateTime arrivalTime;

	@Enumerated(EnumType.STRING)
	private FlightStatus flightStatus = FlightStatus.SCHEDULED;

	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Reservation> reservations = new HashSet<>();

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public void addReservation(Reservation reservation) {
		if(this.capacity > this.reservations.size()) {
			this.reservations.add(reservation);
			reservation.setFlight(this);
		}
		throw new OverbookingException();
	}

	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
		reservation.setFlight(null);
	}
}
