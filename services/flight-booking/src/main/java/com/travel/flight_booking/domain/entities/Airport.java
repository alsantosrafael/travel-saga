package com.travel.flight_booking.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name="AIRPORT")
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true,  length=200)
	private String details;
}
