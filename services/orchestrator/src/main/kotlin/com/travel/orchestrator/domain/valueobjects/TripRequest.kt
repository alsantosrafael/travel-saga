package com.travel.orchestrator.domain.valueobjects

data class TripRequest(
    val userId: String,
    val flightDetails: String,
    )
{
    init {
        require(userId.isNotBlank()) { "UserID is required"}
        require(flightDetails.isNotBlank()) { "Flight details must be provided"}
        require(userId.length <= 100) { "UserID is too long"}
    }
}
