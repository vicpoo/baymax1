package com.vicpoo.baymax.features.domain.usecase

import com.vicpoo.baymax.features.domain.model.Medicine
import com.vicpoo.baymax.features.domain.repository.MedicineRepository

class CreateMedicineUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(medicine: Medicine): Medicine = repository.createMedicine(medicine)
}