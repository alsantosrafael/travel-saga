package com.travel.flight_booking.domain.entities;

import com.travel.flight_booking.domain.enums.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "flight_id")
	private Flight flight;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "passenger_id")
	private Passenger passenger;

	@Column(nullable = false)
	private LocalDateTime reservationTime = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservationStatus status = ReservationStatus.ACTIVE;

	@Column
	private String seatNumber;

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public void setPassenger (Passenger passenger) { this.passenger = passenger;}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Reservation that = (Reservation) o;
		return Objects.equals(id, that.id) && Objects.equals(flight, that.flight) && Objects.equals(passenger, that.passenger) && Objects.equals(reservationTime, that.reservationTime) && status == that.status && Objects.equals(seatNumber, that.seatNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, flight, passenger, reservationTime, status, seatNumber);
	}
	public UUID getId() {
		return id;
	}
	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public Flight getFlight() {
		return flight;
	}
}
