package com.travel.orchestrator.domain.valueobjects

data class FlightInfo(
    val flightCode: String,
    val originCode: String,
    val destinationCode: String,
    val seatNumber: String
) {
    init {
        require(flightCode.isNotBlank()) { "Flight code cannot be blank" }
        require(originCode.isNotBlank()) { "Origin code cannot be blank" }
        require(destinationCode.isNotBlank()) { "Destination code cannot be blank" }
        require(seatNumber.isNotBlank()) { "Seat number cannot be blank" }
        require(originCode != destinationCode) { "Origin and destination cannot be the same" }
    }
}
