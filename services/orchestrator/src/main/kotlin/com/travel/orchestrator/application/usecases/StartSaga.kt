package com.travel.orchestrator.application.usecases

import com.travel.orchestrator.domain.entities.TripRequest
import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequestVO
import com.travel.orchestrator.infrastructure.kafka.producers.BookFlightCommandProducer
import com.travel.orchestrator.infrastructure.repository.TripRequestRepository
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Component

@Component("StartSaga")
class StartSaga(
    private val tripSagaRepository: TripSagaRepository,
    private val tripRequestRepository: TripRequestRepository,
    private val bookFlightCommandProducer: BookFlightCommandProducer
) {

    fun execute(request: TripRequestVO): TripCreatedResponse {
        val tripRequest: TripRequest = tripRequestRepository.save(
            TripRequest(null, request.userId, request.document, request.email, request.toString()))

        val tripSaga = tripSagaRepository.save(TripSaga(null, tripRequest))
        // TODO: create a list of all co-related domain details (flight, hotel, car, etc...)
        // TODO: once with the list of all domain details, create a SagaStep for each of them
        // TODO: send message to topics on flight-booking, hotel-booking, car-rental

        if (request.flightInfo != null) {
            bookFlightCommandProducer.dispatch(
                tripSaga.id.toString(),
                tripSaga.tripRequest?.userId ?: "",
                tripSaga.tripRequest?.email ?: "",
                tripSaga.tripRequest?.document ?: "",
                request.flightInfo
            )
        }

        return TripCreatedResponse(
            tripSaga.id.toString(),
            tripSaga.tripRequest?.userId ?: "",
            tripSaga.tripRequest?.document ?: "",
            tripSaga.tripRequest?.email ?: "",
            tripSaga.currentState
        )
    }
}