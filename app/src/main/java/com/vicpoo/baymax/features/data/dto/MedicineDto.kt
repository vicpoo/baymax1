package com.vicpoo.baymax.features.data.dto

import com.google.gson.annotations.SerializedName

data class MedicineDto(
    @SerializedName("medication_id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("purpose")
    val purpose: String? = null,

    @SerializedName("min_age")
    val minAge: Int? = null,

    @SerializedName("max_age")
    val maxAge: Int? = null,

    @SerializedName("dosage")
    val dosage: String? = null,

    @SerializedName("side_effects")
    val sideEffects: String? = null,

    @SerializedName("contraindications")
    val contraindications: String? = null,

    @SerializedName("requires_prescription")
    val requiresPrescription: Boolean = false,

    @SerializedName("medication_type")
    val medicationType: String? = null,

    @SerializedName("manufacturer")
    val manufacturer: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null
)

data class MedicineRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("purpose")
    val purpose: String? = null,

    @SerializedName("min_age")
    val minAge: Int? = null,

    @SerializedName("max_age")
    val maxAge: Int? = null,

    @SerializedName("dosage")
    val dosage: String? = null,

    @SerializedName("side_effects")
    val sideEffects: String? = null,

    @SerializedName("contraindications")
    val contraindications: String? = null,

    @SerializedName("requires_prescription")
    val requiresPrescription: Boolean = false,

    @SerializedName("medication_type")
    val medicationType: String? = null,

    @SerializedName("manufacturer")
    val manufacturer: String? = null
)