package com.travel.orchestrator.infrastructure.kafka.producers

interface ReservationSender<T> {

    fun dispatch(sagaId: String, userId: String, email: String, document: String, details: T)
}