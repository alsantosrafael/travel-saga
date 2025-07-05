package com.travel.orchestrator.infrastructure.repository

import com.travel.orchestrator.domain.entities.SagaStepState
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface SagaStepStateRepository: JpaRepository<SagaStepState, Long>{

    fun findFirstByTripSaga_IdAndSagaStepType(sagaId: UUID, sagaStepState: String): Optional<SagaStepState>
}