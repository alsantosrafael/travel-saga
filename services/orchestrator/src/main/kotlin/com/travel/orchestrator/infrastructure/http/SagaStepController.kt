package com.travel.orchestrator.infrastructure.http

import com.travel.orchestrator.domain.entities.SagaStepState
import com.travel.orchestrator.infrastructure.repository.SagaStepStateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import kotlin.uuid.Uuid

@RestController
@RequestMapping("/steps")
class SagaStepController(
    private val sagaStepStateRepository: SagaStepStateRepository
) {

    @GetMapping
    fun fetchAll(): List<SagaStepState> {
        return sagaStepStateRepository.findAll();
    }

    @GetMapping("/{sagaId}")
    fun fetchBySagaId(@PathVariable sagaId: String): List<SagaStepState> {
        return sagaStepStateRepository.findFirstByTripSaga_Id(UUID.fromString(sagaId))
    }

}