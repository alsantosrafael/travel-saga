package com.travel.orchestrator.infrastructure.kafka.producers

import com.travel.orchestrator.avro.TripSagaCreatedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TripSagaKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, TripSagaCreatedEvent>
) {
    fun send(sagaId: String, userId: String, flightDetails: String) {
        val event = TripSagaCreatedEvent(sagaId, userId, flightDetails)
        kafkaTemplate.send("trip-saga-created", sagaId, event)
    }
}