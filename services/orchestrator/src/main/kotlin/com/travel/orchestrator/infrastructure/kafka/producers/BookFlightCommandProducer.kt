package com.travel.orchestrator.infrastructure.kafka.producers

import com.travel.orchestrator.avro.BookFlightCommand
import com.travel.orchestrator.domain.mappers.BookFlightCommandMapper
import com.travel.orchestrator.domain.valueobjects.FlightInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class BookFlightCommandProducer(
    val logger: Logger = LoggerFactory.getLogger(BookFlightCommandProducer::class.java),
    private val kafkaTemplate: KafkaTemplate<String, BookFlightCommand>,
    private val topic: String = "create-flight"
) : ReservationSender<FlightInfo>{
    override fun dispatch(
        sagaId: String,
        userId: String,
        email: String,
        document: String,
        details: FlightInfo
    ) {
        try {
            val message = BookFlightCommandMapper.toAvroBookFlightCommand(
                sagaId,
                userId,
                email,
                document,
                details
            )
            kafkaTemplate.send(topic, sagaId, message).whenComplete { result, ex ->
                if (ex == null) {
                    logger.info("Message sent successfully to topic {} with key {}", topic, message.sagaId)
                } else {
                    logger.error(
                        "Failed to send message to topic {} with key {}: {}",
                        topic,
                        message.sagaId,
                        ex.message
                    )
                }
            }
        } catch (e: Exception) {
            logger.error("Deu pau: {}", {});
            throw e;
        }
    }
}