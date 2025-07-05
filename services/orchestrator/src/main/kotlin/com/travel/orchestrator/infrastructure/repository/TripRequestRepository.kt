package com.travel.orchestrator.infrastructure.repository

import com.travel.orchestrator.domain.entities.TripRequest
import org.springframework.data.jpa.repository.JpaRepository

interface TripRequestRepository: JpaRepository<TripRequest, Long> {
}