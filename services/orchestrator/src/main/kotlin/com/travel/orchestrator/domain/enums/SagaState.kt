package com.travel.orchestrator.domain.enums

enum class SagaState {
    STARTED,
    FLIGHT_PENDING,
    FLIGHT_DONE,
    FLIGHT_IGNORED,
    FLIGHT_ERROR,
    CAR_PENDING,
    CAR_DONE,
    CAR_IGNORED,
    CAR_ERROR,
    HOTEL_PENDING,
    HOTEL_DONE,
    HOTEL_IGNORED,
    HOTEL_ERROR,
    PAYMENT_PENDING,
    PAYMENT_DONE,
    PAYMENT_ERROR,
    COMPLETED
}