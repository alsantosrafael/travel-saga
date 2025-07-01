package com.travel.orchestrator.domain.valueobjects

import com.travel.orchestrator.domain.enums.SagaState

data class TripCreatedResponse(
    val id: Long,
    val sagaId: String,
    val userId: String,
    val flightDetails: String,
    val flightReservationId: String? = null,
    val currentState: SagaState,
) {
}