package com.activegroupintl.app.data.model

data class StatItem(
    val value: String,
    val label: String
)

data class QuickAction(
    val id: String,
    val title: String,
    val description: String,
    val badgeText: String,
    val badgeType: QuickActionBadgeType,
    val iconName: String
)

enum class QuickActionBadgeType {
    SUCCESS, WARNING, INFO, PRIMARY
}

data class Destination(
    val id: String,
    val country: String,
    val flagEmoji: String,
    val badge: String,
    val highlight1: String,
    val highlight2: String,
    val description: String = "",
    val popularCourses: List<String> = emptyList()
)

data class SuccessStory(
    val id: String,
    val studentName: String,
    val university: String,
    val degree: String,
    val intake: String,
    val countryFlag: String,
    val status: String = "Visa Granted"
)

data class Course(
    val id: String,
    val title: String,
    val category: String, // "Language" or "Test Prep"
    val target: String,
    val duration: String,
    val mode: String, // "Online Zoom & Nayapaltan"
    val fee: String,
    val features: List<String>,
    val nextBatchDate: String
)

data class ConsultationBooking(
    val studentName: String,
    val phone: String,
    val email: String,
    val preferredService: String,
    val mode: String, // Online or In-person
    val date: String,
    val timeSlot: String,
    val notes: String = ""
)
