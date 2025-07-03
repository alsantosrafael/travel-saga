package com.travel.orchestrator.domain.mappers

import com.travel.orchestrator.avro.TripSagaCreatedEvent
import com.travel.orchestrator.domain.entities.TripSaga

object TripSagaEventMapper  {
    fun toEvent(trip: TripSaga): TripSagaCreatedEvent =
        TripSagaCreatedEvent.newBuilder()
            .setSagaId(trip.sagaId)
            .setUserId(trip.userId)
            .setDocument(trip.document)
            .setEmail(trip.email)
            .setFlightDetails(trip.flightDetails)
            .setCarDetails(trip.carDetails)
            .setHotelDetails(trip.hotelDetails)
            .build()
}
