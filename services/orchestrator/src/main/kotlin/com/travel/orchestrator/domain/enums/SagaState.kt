package com.travel.orchestrator.domain.enums

enum class SagaState { STARTED, IN_PROGRESS, COMPLETED, FAILED, COMPENSATING, COMPENSATED }
enum class StepStatus { PENDING, DONE, IGNORED, ERROR, COMPENSATED }
enum class SagaStepType { FLIGHT, HOTEL, CAR, PAYMENT, NOTIFICATION }