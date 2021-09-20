package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "No middle name mentioned",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "No specialization mentioned",
    val specialization: String = "No specialization mentioned",
    val prevEducation: String? = "No previous education mentioned",
)
