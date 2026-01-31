package com.vicpoo.baymax.features.domain.usecase

import com.vicpoo.baymax.features.domain.model.Medicine
import com.vicpoo.baymax.features.domain.repository.MedicineRepository

class GetAllMedicinesUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(): List<Medicine> = repository.getAllMedicines()
}