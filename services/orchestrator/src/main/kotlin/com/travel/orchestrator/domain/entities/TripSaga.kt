package com.travel.orchestrator.domain.entities

import com.travel.orchestrator.domain.enums.SagaState
import java.time.LocalDateTime

data class TripSaga(
    val id: Long,
    val sagaId: String,
    val userId: String,
    val flightDetails: String,
    val flightReservationId: String? = null,
    val currentState: SagaState,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {

    fun updateStatus( newStatus: SagaState, reservationId: String?): TripSaga {
        return copy(
            currentState = newStatus,
            flightReservationId = reservationId ?: this.flightReservationId
        )
    }

}
