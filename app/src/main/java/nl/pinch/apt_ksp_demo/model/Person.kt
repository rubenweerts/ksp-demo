package nl.pinch.apt_ksp_demo.model

import nl.pinch.annotations.PinchEmail

@PinchEmail
data class Person(
    val firstName: String,
    val lastName: String,
)
