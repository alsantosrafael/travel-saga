package com.travel.orchestrator.infrastructure.kafka.consumers

import com.travel.flight_booking.avro.FlightBookedResponse
import com.travel.orchestrator.application.service.TripSagaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class FlightBookedResponseConsumer(
    private val tripSagaService: TripSagaService
) {
    val logger: Logger = LoggerFactory.getLogger(FlightBookedResponseConsumer::class.java)

    @KafkaListener(
        topics = ["create-flight-response"],
        groupId = "orchestrator-service",
        containerFactory = "kafkaListenerBookedFlightResponse"
    )
    fun consume(event: FlightBookedResponse, ack: Acknowledgment) {
        try {
            logger.info("Received event: {}", event);
            tripSagaService.updateWithFlight(event)
            ack.acknowledge();
        } catch (error: Exception) {
            logger.error("Error consuming message e={}", error.localizedMessage)
        }
    }
}