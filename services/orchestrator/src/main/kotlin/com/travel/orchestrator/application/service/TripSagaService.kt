package com.travel.orchestrator.application.service

import com.travel.flight_booking.avro.FlightBookedResponse
import com.travel.orchestrator.application.usecases.StartSaga
import com.travel.orchestrator.application.usecases.UpdateSagaWithFlight
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequestVO
import com.travel.orchestrator.domain.valueobjects.TripResponse
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Service

@Service
class TripSagaService (
    private val startSaga: StartSaga,
    private val updateSagaWithFlight: UpdateSagaWithFlight,
    private val tripSagaRepository: TripSagaRepository,
) {

    fun create(request: TripRequestVO): TripCreatedResponse {
        return startSaga.execute(request);
    }

    fun updateWithFlight(request: FlightBookedResponse) {
        return updateSagaWithFlight.execute(request);
    }

    fun fetch(): List<TripResponse> =
     tripSagaRepository.findAll().map { tripSaga ->
        TripResponse(
            id = tripSaga.id,
            userId = tripSaga.tripRequest!!.userId ,
            email = tripSaga.tripRequest!!.email,
            document = tripSaga.tripRequest!!.document,
            currentState = tripSaga.currentState
        )
    }
}