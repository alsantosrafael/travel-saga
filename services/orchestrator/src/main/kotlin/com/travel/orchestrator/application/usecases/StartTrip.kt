package com.travel.orchestrator.application.usecases

import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import com.travel.orchestrator.infrastructure.kafka.producers.TripSagaKafkaProducer
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component("StartTrip")
class StartTrip (
    private val repository: TripSagaRepository,
    private val kafkaProducer: TripSagaKafkaProducer
) {

    fun execute(request: TripRequest): TripCreatedResponse {
        val trip: TripSaga = TripSaga(
            null,
            UUID.randomUUID().toString(),
            request.userId,
            request.document,
            request.email,
            request.flightDetails,
            request.carDetails,
            request.hotelDetails
        )
        val tripSaga = repository.save(trip);
        kafkaProducer.send(tripSaga)
        return TripCreatedResponse(
            tripSaga.id,
            tripSaga.sagaId,
            tripSaga.userId,
            tripSaga.document,
            tripSaga.email,
            tripSaga.flightDetails,
            tripSaga.flightReservationId,
            tripSaga.hotelDetails,
            tripSaga.hotelReservationId,
            tripSaga.carDetails,
            tripSaga.carReservationId,
            tripSaga.currentState,
        )
    }
}