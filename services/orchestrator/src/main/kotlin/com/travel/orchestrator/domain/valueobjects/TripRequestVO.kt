package com.travel.orchestrator.domain.valueobjects

data class TripRequestVO(
    val userId: String,
    val document: String,
    val email: String,
    val flightInfo: FlightInfo? = null,
    val carInfo: String? = null,
    val hotelInfo: String? = null,
    )
{
    init {
        require(userId.isNotBlank()) { "UserID is required"}
        require(email.isNotBlank()) { "User email is required"}
        require(document.isNotBlank()) { "User document is required"}
        require(userId.length <= 100) { "UserID is too long"}
        require(Regex("\\d{11}").matches(document)) {"Document should have 11 digits"}
        require(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$").matches(email)) { "Email must follow pattern myname@myprovider.com"}
    }
}
