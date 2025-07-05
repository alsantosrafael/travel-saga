package com.travel.orchestrator.infrastructure.kafka.consumers

import com.travel.orchestrator.avro.FlightBookedResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment

class FlightBookedResponseConsumer {
    val logger: Logger = LoggerFactory.getLogger(FlightBookedResponseConsumer::class.java)

    @KafkaListener(
        topics = ["create-flight-response"],
        groupId = "orchestrator-service",
        containerFactory = "kafkaListenerBookedFlightResponse"
    )
    fun consume(event: FlightBookedResponse, ack: Acknowledgment) {
        logger.info("Received event: {}", event);
        ack.acknowledge();
    }
}