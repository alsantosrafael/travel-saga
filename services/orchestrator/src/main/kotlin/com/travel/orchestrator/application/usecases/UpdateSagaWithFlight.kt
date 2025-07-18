package com.travel.orchestrator.application.usecases

import com.travel.flight_booking.avro.FlightBookedResponse
import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.enums.SagaState
import com.travel.orchestrator.domain.enums.SagaStepType
import com.travel.orchestrator.domain.enums.StepStatus
import com.travel.orchestrator.infrastructure.repository.SagaStepStateRepository
import com.travel.orchestrator.infrastructure.repository.TripSagaRepository
import org.springframework.stereotype.Component
import java.util.UUID


@Component("UpdateSagaWithFlight")
class UpdateSagaWithFlight(
    private val tripSagaRepository: TripSagaRepository,
    private val sagaStepStateRepository: SagaStepStateRepository,
) {

    fun execute(event: FlightBookedResponse) {
        val saga: TripSaga = tripSagaRepository.findById(
            UUID.fromString(event.sagaId),
        ).orElseThrow { RuntimeException("Saga not found") }

        val sagaStepState = sagaStepStateRepository.findFirstByTripSaga_IdAndSagaStepType(
            saga.id!!,
            SagaStepType.FLIGHT
        ).orElseThrow{ RuntimeException("SagaStep not found")}
        // TODO: update step with state response and reservationID
        // TODO: handle different states from domains, like flight: DONE and car: ERROR
        sagaStepState.reservationId = event.flightReservationId
        sagaStepState.stepStatus = StepStatus.valueOf(event.status)
        sagaStepState.message = event.message
        if(sagaStepState.stepStatus == StepStatus.ERROR) {
            saga.currentState = SagaState.FAILED
        }
        sagaStepStateRepository.save(sagaStepState)
        tripSagaRepository.save(saga);
    }
}