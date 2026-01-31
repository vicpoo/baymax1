package com.vicpoo.baymax.features.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicpoo.baymax.features.domain.model.Medicine
import com.vicpoo.baymax.features.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val getAllMedicinesUseCase: GetAllMedicinesUseCase,
    private val createMedicineUseCase: CreateMedicineUseCase,
    private val updateMedicineUseCase: UpdateMedicineUseCase,
    private val deleteMedicineUseCase: DeleteMedicineUseCase,
    private val searchMedicineUseCase: SearchMedicineUseCase
) : ViewModel() {

    private val _medicines = MutableStateFlow<List<Medicine>>(emptyList())
    val medicines: StateFlow<List<Medicine>> = _medicines.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Medicine>>(emptyList())
    val searchResults: StateFlow<List<Medicine>> = _searchResults.asStateFlow()

    init {
        loadMedicines()
    }

    fun loadMedicines() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val medicinesList = getAllMedicinesUseCase()
                _medicines.value = medicinesList
            } catch (e: Exception) {
                _error.value = "Error al cargar medicamentos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createMedicine(medicine: Medicine) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val newMedicine = createMedicineUseCase(medicine)
                _medicines.value = _medicines.value + newMedicine
            } catch (e: Exception) {
                _error.value = "Error al crear medicamento: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateMedicine(medicine: Medicine) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val updatedMedicine = updateMedicineUseCase(medicine)
                _medicines.value = _medicines.value.map {
                    if (it.id == updatedMedicine.id) updatedMedicine else it
                }
            } catch (e: Exception) {
                _error.value = "Error al actualizar medicamento: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteMedicine(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val success = deleteMedicineUseCase(id)
                if (success) {
                    _medicines.value = _medicines.value.filter { it.id != id }
                } else {
                    _error.value = "No se pudo eliminar el medicamento"
                }
            } catch (e: Exception) {
                _error.value = "Error al eliminar medicamento: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchMedicines(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val results = searchMedicineUseCase(name)
                _searchResults.value = results
            } catch (e: Exception) {
                _error.value = "Error al buscar medicamentos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
    }
}