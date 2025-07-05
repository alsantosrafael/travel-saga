package com.travel.orchestrator.domain.entities

import com.travel.orchestrator.domain.enums.SagaStepType
import com.travel.orchestrator.domain.enums.StepStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "TRIP_SAGA_STEP")
class SagaStepState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="trip_saga_id")
    var tripSaga: TripSaga? = null,

    @Column(nullable = true)
    var reservationId: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var sagaStepType: SagaStepType? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var stepStatus: StepStatus = StepStatus.PENDING,

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,
) {}