package com.travel.orchestrator.application.usecases

import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.enums.SagaState
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

@Component("StartTrip")
class StartTrip {

    fun execute(request: TripRequest): TripCreatedResponse {
        val trip: TripSaga = TripSaga(
            0L,
            UUID.randomUUID().toString(),
            request.userId,
            request.flightDetails,
            null,
            SagaState.STARTED,
            LocalDateTime.now(),
            LocalDateTime.now()
        )

        return TripCreatedResponse(trip.id, trip.sagaId, trip.userId, trip.flightDetails, trip.flightReservationId, trip.currentState)
    }
}