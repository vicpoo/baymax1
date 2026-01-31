package com.vicpoo.baymax.features.domain.usecase

import com.vicpoo.baymax.features.domain.repository.MedicineRepository

class DeleteMedicineUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(id: Int): Boolean = repository.deleteMedicine(id)
}