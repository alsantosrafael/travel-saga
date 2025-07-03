package com.travel.orchestrator.domain.valueobjects

import com.travel.orchestrator.domain.enums.SagaState

data class TripCreatedResponse(
    val id: Long? = null,
    val sagaId: String,
    val userId: String,
    val document: String,
    val email: String,
    val flightDetails: String?= null,
    val flightReservationId: String? = null,
    val hotelDetails: String?= null,
    val hotelReservationId: String? = null,
    val carDetails: String?= null,
    val carReservationId: String? = null,
    val currentState: SagaState,
) {
}