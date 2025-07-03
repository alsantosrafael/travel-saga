package com.travel.orchestrator.infrastructure.kafka.producers

import com.travel.orchestrator.avro.TripSagaCreatedEvent
import com.travel.orchestrator.domain.entities.TripSaga
import com.travel.orchestrator.domain.mappers.TripSagaEventMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TripSagaKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, TripSagaCreatedEvent>
) {
    fun send(trip: TripSaga) {
        val event = TripSagaEventMapper.toEvent(trip)
        kafkaTemplate.send("trip-saga-created", trip.sagaId, event)
    }
}