package com.travel.orchestrator.domain.valueobjects

import com.travel.orchestrator.domain.enums.SagaState

data class TripCreatedResponse(
    val id: String? = null,
    val userId: String,
    val document: String,
    val email: String,
    val currentState: SagaState,
) {
}