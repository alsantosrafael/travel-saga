package com.travel.orchestrator.domain.enums

import java.time.Instant

enum class SagaState { STARTED, IN_PROGRESS, COMPLETED, FAILED, COMPENSATING, COMPENSATED }
enum class StepStatus { PENDING, DONE, IGNORED, ERROR, COMPENSATED }
enum class SagaStepType { FLIGHT, HOTEL, CAR, PAYMENT, NOTIFICATION }

data class SagaStepState(
    val step: SagaStepType,
    var status: StepStatus = StepStatus.PENDING,
    var errorMessage: String? = null,
    var updatedAt: Instant = Instant.now()
)