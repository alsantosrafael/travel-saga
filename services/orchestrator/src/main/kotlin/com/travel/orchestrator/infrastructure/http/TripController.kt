package com.travel.orchestrator.infrastructure.http

import com.travel.orchestrator.application.service.TripSagaService
import com.travel.orchestrator.domain.valueobjects.TripCreatedResponse
import com.travel.orchestrator.domain.valueobjects.TripRequest
import com.travel.orchestrator.domain.valueobjects.TripResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI

@RestController
@RequestMapping("/trips")
class TripController(
    private val service: TripSagaService
) {

    @PostMapping
    fun createTrip(@RequestBody request: TripRequest): ResponseEntity<TripCreatedResponse> {
        return ResponseEntity.created(URI.create("/trips/${0L}")).body(service.create(request))
    }

    @GetMapping
    fun fetchTrips(): ResponseEntity<List<TripResponse>> {
        return ResponseEntity.ok().body(service.fetch())
    }
}