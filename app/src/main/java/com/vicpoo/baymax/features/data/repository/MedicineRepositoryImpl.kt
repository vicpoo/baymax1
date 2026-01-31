package com.vicpoo.baymax.features.data.repository

import com.vicpoo.baymax.features.data.dto.MedicineRequest
import com.vicpoo.baymax.features.data.mapper.toDomain
import com.vicpoo.baymax.features.data.mapper.toRequest
import com.vicpoo.baymax.features.data.remote.MedicineApi
import com.vicpoo.baymax.features.domain.model.Medicine
import com.vicpoo.baymax.features.domain.repository.MedicineRepository

class MedicineRepositoryImpl(
    private val api: MedicineApi
) : MedicineRepository {

    override suspend fun getAllMedicines(): List<Medicine> {
        return try {
            api.getAllMedicines().map { it.toDomain() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMedicineById(id: Int): Medicine {
        return try {
            api.getMedicineById(id).toDomain()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun createMedicine(medicine: Medicine): Medicine {
        return try {
            api.createMedicine(medicine.toRequest()).toDomain()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateMedicine(medicine: Medicine): Medicine {
        return try {
            api.updateMedicine(medicine.id, medicine.toRequest()).toDomain()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deleteMedicine(id: Int): Boolean {
        return try {
            val response = api.deleteMedicine(id)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun searchMedicines(name: String): List<Medicine> {
        return try {
            api.searchMedicines(name).map { it.toDomain() }
        } catch (e: Exception) {
            throw e
        }
    }
}