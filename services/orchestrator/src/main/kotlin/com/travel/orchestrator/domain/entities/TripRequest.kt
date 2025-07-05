package com.travel.orchestrator.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name="TRIP_REQUEST")
class TripRequest (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var userId: String = "",

    @Column(nullable = false)
    var document: String = "",

    @Column(nullable = false)
    var email: String = "",

    // TODO: implement @Convert(converter = DetailsConverter::class)
    // TODO: var details: Details? = null
    @Column(columnDefinition = "TEXT")
    var payloadJson: String? = null
) {}