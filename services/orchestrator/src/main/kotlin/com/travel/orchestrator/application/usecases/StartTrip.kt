package com.travel.orchestrator.application.usecases

import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component("StartTrip")
class StartTrip (
    private val repository: TripSagaRepository
) {

    fun execute(request: TripRequest): TripCreatedResponse {
        val trip: TripSaga = TripSaga(
            null,
            UUID.randomUUID().toString(),
            request.userId,
            request.flightDetails,
        )
        val savedTrip = repository.save(
            trip
        );

        return TripCreatedResponse(savedTrip.id ?: 0L, savedTrip.sagaId, savedTrip.userId, savedTrip.flightDetails, savedTrip.flightReservationId, savedTrip.currentState)
    }
}