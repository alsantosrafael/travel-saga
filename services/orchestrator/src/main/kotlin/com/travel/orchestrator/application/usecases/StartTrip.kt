package com.travel.orchestrator.application.usecases

import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import com.travel.orchestrator.infrastructure.kafka.producers.BookFlightCommandProducer
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component("StartTrip")
class StartTrip (
    private val repository: TripSagaRepository,
    private val bookFlightCommandProducer: BookFlightCommandProducer
) {

    fun execute(request: TripRequest): TripCreatedResponse {
        val trip: TripSaga = TripSaga(
            null,
            UUID.randomUUID().toString(),
            request.userId,
            request.document,
            request.email,
            request.flightInfo,
            request.carDetails,
            request.hotelDetails
        )
        val tripSaga = repository.save(trip);

        if(tripSaga.flightInfo != null) {
            val flightDetails = tripSaga.flightInfo!!
            bookFlightCommandProducer.dispatch(tripSaga.sagaId, tripSaga.userId, tripSaga.email, tripSaga.document, flightDetails)
        }

        return TripCreatedResponse(
            tripSaga.id,
            tripSaga.sagaId,
            tripSaga.userId,
            tripSaga.document,
            tripSaga.email,
            tripSaga.flightInfo,
            tripSaga.flightReservationId,
            tripSaga.hotelDetails,
            tripSaga.hotelReservationId,
            tripSaga.carDetails,
            tripSaga.carReservationId,
            tripSaga.currentState,
        )
    }
}