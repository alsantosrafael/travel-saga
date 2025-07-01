package com.travel.orchestrator.infrastructure.repository

import com.travel.orchestrator.domain.entities.TripSaga
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TripSagaRepository: JpaRepository<TripSaga, Long> {

}