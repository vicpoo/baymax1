//MedicineScreen
package com.vicpoo.baymax.features.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicpoo.baymax.features.presentation.components.MedicineCard
import com.vicpoo.baymax.features.presentation.components.MedicineDialog
import com.vicpoo.baymax.features.presentation.viewmodels.MedicineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineScreen(viewModel: MedicineViewModel) {
    val medicines by viewModel.medicines.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedMedicine by remember { mutableStateOf<com.vicpoo.baymax.features.domain.model.Medicine?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    val displayMedicines = if (isSearching && searchResults.isNotEmpty()) {
        searchResults
    } else {
        medicines
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BAYMAX",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 1.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A0E21),
                    scrolledContainerColor = Color(0xFF0A0E21)
                ),
                actions = {
                    IconButton(
                        onClick = { isSearching = !isSearching },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF2E3192),
                                            Color(0xFF1BFFFF)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (isSearching) Icons.Default.Close else Icons.Default.Search,
                                contentDescription = if (isSearching) "Cerrar búsqueda" else "Buscar",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                modifier = Modifier.shadow(
                    elevation = 8.dp,
                    spotColor = Color(0xFF1BFFFF),
                    ambientColor = Color(0xFF2E3192)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = Color(0xFF0A0E21),
                contentColor = Color.White,
                modifier = Modifier
                    .size(64.dp)
                    .shadow(
                        elevation = 12.dp,
                        spotColor = Color(0xFF1BFFFF),
                        ambientColor = Color(0xFF2E3192)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape) // Usar CircleShape aquí también
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF2E3192),
                                    Color(0xFF1BFFFF)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Agregar",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        containerColor = Color(0xFFF5F7FA)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF5F7FA),
                            Color(0xFFE8F4F8)
                        )
                    )
                )
        ) {
            // Barra de búsqueda flotante
            if (isSearching) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF0A0E21).copy(alpha = 0.9f),
                                    Color(0xFF0A0E21).copy(alpha = 0.7f)
                                )
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            if (it.length >= 2) {
                                viewModel.searchMedicines(it)
                            } else {
                                viewModel.clearSearch()
                            }
                        },
                        label = {
                            Text(
                                "Buscar medicamento...",
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF1BFFFF),
                            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                            focusedLabelColor = Color(0xFF1BFFFF),
                            unfocusedLabelColor = Color.White.copy(alpha = 0.8f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color(0xFF1BFFFF)
                        ),
                        shape = MaterialTheme.shapes.large
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = if (isSearching) 100.dp else 0.dp,
                        bottom = 16.dp
                    )
            ) {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color(0xFF1BFFFF),
                            strokeWidth = 4.dp,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            "Sincronizando con BAYMAX...",
                            color = Color(0xFF0A0E21),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Obteniendo datos médicos",
                            color = Color(0xFF666666),
                            fontSize = 14.sp
                        )
                    }
                } else if (error != null) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFFD32F2F),
                                            Color(0xFFB71C1C)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "⚠️",
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Error de conexión",
                            color = Color(0xFF0A0E21),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            error ?: "Error desconocido",
                            color = Color(0xFF666666),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = { viewModel.loadMedicines() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0A0E21)
                            ),
                            modifier = Modifier
                                .height(56.dp)
                                .padding(horizontal = 32.dp),
                            shape = MaterialTheme.shapes.large
                        ) {
                            Text(
                                "Reintentar conexión",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                } else if (displayMedicines.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape) // Usar CircleShape
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFF2E3192),
                                            Color(0xFF1BFFFF)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "💊",
                                fontSize = 48.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = if (isSearching) "No se encontraron resultados"
                            else "Inventario vacío",
                            color = Color(0xFF0A0E21),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            if (isSearching) "Intenta con otro término de búsqueda"
                            else "Comienza agregando tu primer medicamento",
                            color = Color(0xFF666666),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                        if (!isSearching) {
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(
                                onClick = { showAddDialog = true },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF0A0E21)
                                ),
                                modifier = Modifier
                                    .height(56.dp)
                                    .padding(horizontal = 32.dp),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Text(
                                    "➕ Agregar Primer Medicamento",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(displayMedicines) { medicine ->
                            MedicineCard(
                                medicine = medicine,
                                onEditClick = {
                                    selectedMedicine = medicine
                                    showEditDialog = true
                                },
                                onDeleteClick = {
                                    viewModel.deleteMedicine(medicine.id)
                                }
                            )
                        }
                    }
                }
            }

            // Diálogos
            if (showAddDialog) {
                MedicineDialog(
                    title = "NUEVO MEDICAMENTO",
                    onDismiss = { showAddDialog = false },
                    onSave = { medicine ->
                        viewModel.createMedicine(medicine)
                        showAddDialog = false
                    }
                )
            }

            selectedMedicine?.let { medicine ->
                if (showEditDialog) {
                    MedicineDialog(
                        title = "EDITAR MEDICAMENTO",
                        medicine = medicine,
                        onDismiss = {
                            showEditDialog = false
                            selectedMedicine = null
                        },
                        onSave = { updatedMedicine ->
                            viewModel.updateMedicine(updatedMedicine)
                            showEditDialog = false
                            selectedMedicine = null
                        }
                    )
                }
            }
        }
    }
}