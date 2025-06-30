package com.travel.orchestrator.domain.valueobjects

import com.travel.orchestrator.domain.enums.SagaState
import java.time.LocalDateTime

data class TripRequest(
    val userId: String,
    val status: SagaState,
    val flightDetails: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
    )
