package com.vicpoo.baymax.core.di

import com.vicpoo.baymax.core.network.RetrofitProvider
import com.vicpoo.baymax.features.data.remote.MedicineApi
import com.vicpoo.baymax.features.data.repository.MedicineRepositoryImpl
import com.vicpoo.baymax.features.domain.repository.MedicineRepository
import com.vicpoo.baymax.features.domain.usecase.*

object Di {
    private val medicineApi: MedicineApi by lazy {
        RetrofitProvider.retrofit.create(MedicineApi::class.java)
    }

    private val medicineRepository: MedicineRepository by lazy {
        MedicineRepositoryImpl(medicineApi)
    }

    val getAllMedicinesUseCase: GetAllMedicinesUseCase by lazy {
        GetAllMedicinesUseCase(medicineRepository)
    }

    val getMedicineByIdUseCase: GetMedicineByIdUseCase by lazy {
        GetMedicineByIdUseCase(medicineRepository)
    }

    val createMedicineUseCase: CreateMedicineUseCase by lazy {
        CreateMedicineUseCase(medicineRepository)
    }

    val updateMedicineUseCase: UpdateMedicineUseCase by lazy {
        UpdateMedicineUseCase(medicineRepository)
    }

    val deleteMedicineUseCase: DeleteMedicineUseCase by lazy {
        DeleteMedicineUseCase(medicineRepository)
    }

    val searchMedicineUseCase: SearchMedicineUseCase by lazy {
        SearchMedicineUseCase(medicineRepository)
    }
}