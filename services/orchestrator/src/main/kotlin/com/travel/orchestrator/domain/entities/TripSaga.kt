package com.travel.orchestrator.domain.entities

import com.travel.orchestrator.domain.enums.SagaState
import jakarta.persistence.Column
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
    var flightDetails: String = "",

    @Column(nullable = true)
    var flightReservationId: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var currentState: SagaState = SagaState.STARTED,

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
)
