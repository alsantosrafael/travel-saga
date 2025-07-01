package com.travel.flight_booking.domain.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="PASSENGER")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String document;
}
