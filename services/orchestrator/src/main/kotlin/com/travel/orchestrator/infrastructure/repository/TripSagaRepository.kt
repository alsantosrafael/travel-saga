package com.travel.orchestrator.infrastructure.repository

import com.travel.orchestrator.domain.entities.TripSaga
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TripSagaRepository: JpaRepository<TripSaga, UUID> {
}