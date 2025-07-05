package com.travel.orchestrator.domain.valueobjects

import com.travel.orchestrator.domain.enums.SagaState
import java.util.UUID

data class TripResponse(
    val id: UUID?,
    val userId: String,
    val document: String,
    val email: String,
    val flightInfo: FlightInfo? = null,
    val hotelDetails: String? = null,
    val carDetails: String? = null,
    val flightReservationId: String? = null,
    val hotelReservationId: String? = null,
    val carReservationId: String? = null,
    val currentState: SagaState,
)
