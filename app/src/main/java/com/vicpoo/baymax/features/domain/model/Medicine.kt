package com.vicpoo.baymax.features.domain.model

data class Medicine(
    val id: Int,
    val name: String,
    val description: String,
    val purpose: String,
    val minAge: Int,
    val maxAge: Int,
    val dosage: String,
    val sideEffects: String,
    val contraindications: String,
    val requiresPrescription: Boolean,
    val medicationType: String,
    val manufacturer: String,
    val createdAt: String
)