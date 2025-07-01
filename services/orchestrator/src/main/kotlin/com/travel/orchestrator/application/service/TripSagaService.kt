package com.travel.orchestrator.application.service

import com.travel.orchestrator.application.usecases.StartTrip
import com.travel.orchestrator.domain.enums.SagaState
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import com.travel.orchestrator.domain.valueobjects.TripResponse
import org.springframework.stereotype.Service

@Service
class TripSagaService (
    private val startTrip: StartTrip
) {

    fun create(request: TripRequest): TripCreatedResponse {
        return startTrip.execute(request);
    }

    fun fetch(): List<TripResponse> {
        return listOf(
            TripResponse(
                id = 1L,
                sagaId = "uuid-1",
                userId = "user1",
                flightDetails = "GRU-NYC",
                flightReservationId = null,
                currentState = SagaState.STARTED,
            ),
        )
    }
}