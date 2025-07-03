package com.travel.orchestrator.infrastructure.kafka.producers

import com.travel.orchestrator.avro.BookFlightCommand
import com.travel.orchestrator.domain.mappers.BookFlightCommandMapper
import com.travel.orchestrator.domain.valueobjects.FlightInfo
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class BookFlightCommandProducer(
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
        val message = BookFlightCommandMapper.toAvroBookFlightCommand(
            sagaId,
            userId,
            email,
            document,
            details
        )
        kafkaTemplate.send(topic, sagaId,message)
    }
}