package com.vicpoo.baymax.features.data.mapper

import com.vicpoo.baymax.features.data.dto.MedicineDto
import com.vicpoo.baymax.features.data.dto.MedicineRequest
import com.vicpoo.baymax.features.domain.model.Medicine

fun MedicineDto.toDomain(): Medicine {
    return Medicine(
        id = id ?: 0,
        name = name,
        description = description ?: "",
        purpose = purpose ?: "",
        minAge = minAge ?: 0,
        maxAge = maxAge ?: 0,
        dosage = dosage ?: "",
        sideEffects = sideEffects ?: "",
        contraindications = contraindications ?: "",
        requiresPrescription = requiresPrescription,
        medicationType = medicationType ?: "",
        manufacturer = manufacturer ?: "",
        createdAt = createdAt ?: ""
    )
}

fun Medicine.toRequest(): MedicineRequest {
    return MedicineRequest(
        name = name,
        description = if (description.isNotEmpty()) description else null,
        purpose = if (purpose.isNotEmpty()) purpose else null,
        minAge = if (minAge > 0) minAge else null,
        maxAge = if (maxAge > 0) maxAge else null,
        dosage = if (dosage.isNotEmpty()) dosage else null,
        sideEffects = if (sideEffects.isNotEmpty()) sideEffects else null,
        contraindications = if (contraindications.isNotEmpty()) contraindications else null,
        requiresPrescription = requiresPrescription,
        medicationType = if (medicationType.isNotEmpty()) medicationType else null,
        manufacturer = if (manufacturer.isNotEmpty()) manufacturer else null
    )
}