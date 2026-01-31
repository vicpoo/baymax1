package com.vicpoo.baymax.features.domain.usecase

import com.vicpoo.baymax.features.domain.model.Medicine
import com.vicpoo.baymax.features.domain.repository.MedicineRepository

class SearchMedicineUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(name: String): List<Medicine> = repository.searchMedicines(name)
}