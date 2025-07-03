package com.travel.orchestrator.domain.mappers

import com.travel.orchestrator.avro.BookFlightCommand
import com.travel.orchestrator.avro.FlightDetails
import com.travel.orchestrator.domain.valueobjects.FlightInfo
import org.springframework.stereotype.Component

@Component
object BookFlightCommandMapper {
    fun toAvroBookFlightCommand(
        sagaId: String,
        userId: String,
        email: String,
        document: String,
        details: FlightInfo): BookFlightCommand {
        return BookFlightCommand.newBuilder()
            .setSagaId(sagaId)
            .setUserId(userId)
            .setDocument(document)
            .setEmail(email)
            .setFlightDetails(this.toAvroFlightDetails(details))
            .build()
    }

    fun toAvroFlightDetails(domain: FlightInfo): FlightDetails {
        return FlightDetails.newBuilder()
            .setFlightCode(domain.flightCode)
            .setOriginCode(domain.originCode)
            .setDestinationCode(domain.destinationCode)
            .setSeatNumber(domain.seatNumber)
            .build()
    }
}