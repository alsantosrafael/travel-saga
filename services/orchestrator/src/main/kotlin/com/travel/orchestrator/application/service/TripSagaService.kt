package com.travel.orchestrator.application.service

import com.travel.orchestrator.application.usecases.StartTrip
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import com.travel.orchestrator.domain.valueobjects.TripResponse
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Service

@Service
class TripSagaService (
    private val startTrip: StartTrip,
    private val repository: TripSagaRepository
) {

    fun create(request: TripRequest): TripCreatedResponse {
        // realizar envios de tópicos flight-booking, hotel-booking, car-rental
        return startTrip.execute(request);
    }

    fun fetch(): List<TripResponse> =
     repository.findAll().map { tripSaga ->
        TripResponse(
            id = tripSaga.id,
            sagaId = tripSaga.sagaId,
            userId = tripSaga.userId,
            email = tripSaga.email,
            document = tripSaga.document,
            flightInfo = tripSaga.flightInfo,
            hotelDetails = tripSaga.hotelDetails,
            carDetails = tripSaga.carDetails,
            flightReservationId = tripSaga.flightReservationId,
            hotelReservationId = tripSaga.hotelReservationId,
            carReservationId = tripSaga.carReservationId,
            currentState = tripSaga.currentState
        )
    }
}