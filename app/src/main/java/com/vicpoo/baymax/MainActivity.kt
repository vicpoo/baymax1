package com.vicpoo.baymax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vicpoo.baymax.core.di.Di
import com.vicpoo.baymax.features.presentation.screens.MedicineScreen
import com.vicpoo.baymax.features.presentation.viewmodels.MedicineViewModel
import com.vicpoo.baymax.ui.theme.BaymaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = MedicineViewModel(
            getAllMedicinesUseCase = Di.getAllMedicinesUseCase,
            createMedicineUseCase = Di.createMedicineUseCase,
            updateMedicineUseCase = Di.updateMedicineUseCase,
            deleteMedicineUseCase = Di.deleteMedicineUseCase,
            searchMedicineUseCase = Di.searchMedicineUseCase
        )

        setContent {
            BaymaxTheme {
                MedicineScreen(viewModel = viewModel)
            }
        }
    }
}