package com.travel.orchestrator.domain.entities

import com.travel.orchestrator.domain.enums.SagaState
import com.travel.orchestrator.domain.valueobjects.FlightInfo
import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "TRIP_SAGA")
class TripSaga(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var sagaId: String = "",

    @Column(nullable = false)
    var userId: String = "",

    @Column(nullable = false)
    var document: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "flightCode", column = Column(name = "flightCode")),
        AttributeOverride(name = "originCode", column = Column(name = "originCode")),
        AttributeOverride(name = "destinationCode", column = Column(name = "destinationCode")),
        AttributeOverride(name = "seatNumber", column = Column(name = "seatNumber"))
    )
    var flightInfo: FlightInfo? = null,

    @Column(nullable = true)
    var carDetails: String? = null,

    @Column(nullable = true)
    var hotelDetails: String? = null,

    @Column(nullable = true)
    var flightReservationId: String? = null,

    @Column(nullable = true)
    var carReservationId: String? = null,

    @Column(nullable = true)
    var hotelReservationId: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var currentState: SagaState = SagaState.STARTED,

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
)
