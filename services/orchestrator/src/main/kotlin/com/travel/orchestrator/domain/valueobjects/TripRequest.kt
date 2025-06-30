package com.travel.orchestrator.domain.valueobjects

data class TripRequest(
    val userId: String,
    val flightDetails: String,
    )
{
    init {
        require(userId.isNotBlank()) { "User ID is required"}
        require(flightDetails.isNotBlank()) { "Flight details must be provided"}
    }
}
