package com.vicpoo.baymax.features.domain.repository

import com.vicpoo.baymax.features.domain.model.Medicine

interface MedicineRepository {
    suspend fun getAllMedicines(): List<Medicine>
    suspend fun getMedicineById(id: Int): Medicine
    suspend fun createMedicine(medicine: Medicine): Medicine
    suspend fun updateMedicine(medicine: Medicine): Medicine
    suspend fun deleteMedicine(id: Int): Boolean
    suspend fun searchMedicines(name: String): List<Medicine>
}