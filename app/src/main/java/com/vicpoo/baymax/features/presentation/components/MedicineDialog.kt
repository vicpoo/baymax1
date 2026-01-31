//MedicineDialog.kt
package com.vicpoo.baymax.features.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vicpoo.baymax.features.domain.model.Medicine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDialog(
    title: String,
    medicine: Medicine? = null,
    onDismiss: () -> Unit,
    onSave: (Medicine) -> Unit
) {
    var name by remember { mutableStateOf(medicine?.name ?: "") }
    var description by remember { mutableStateOf(medicine?.description ?: "") }
    var purpose by remember { mutableStateOf(medicine?.purpose ?: "") }
    var minAge by remember { mutableStateOf(medicine?.minAge?.toString() ?: "") }
    var maxAge by remember { mutableStateOf(medicine?.maxAge?.toString() ?: "") }
    var dosage by remember { mutableStateOf(medicine?.dosage ?: "") }
    var sideEffects by remember { mutableStateOf(medicine?.sideEffects ?: "") }
    var contraindications by remember { mutableStateOf(medicine?.contraindications ?: "") }
    var requiresPrescription by remember { mutableStateOf(medicine?.requiresPrescription ?: false) }
    var medicationType by remember { mutableStateOf(medicine?.medicationType ?: "") }
    var manufacturer by remember { mutableStateOf(medicine?.manufacturer ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 700.dp)
                .padding(horizontal = 16.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1C1F2E)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
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
                    Text(
                        text = title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.5.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2A2D3E)
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "INFORMACIÓN BÁSICA",
                                color = Color(0xFF1BFFFF),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                label = {
                                    Text(
                                        "Nombre del medicamento *",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )

                            OutlinedTextField(
                                value = description,
                                onValueChange = { description = it },
                                label = {
                                    Text(
                                        "Descripción",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )

                            OutlinedTextField(
                                value = purpose,
                                onValueChange = { purpose = it },
                                label = {
                                    Text(
                                        "Propósito/Indicación",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2A2D3E)
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "DOSIFICACIÓN Y EDAD",
                                color = Color(0xFF1BFFFF),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                OutlinedTextField(
                                    value = minAge,
                                    onValueChange = { minAge = it },
                                    label = {
                                        Text(
                                            "Edad mínima",
                                            color = Color.White.copy(alpha = 0.7f)
                                        )
                                    },
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFF1BFFFF),
                                        unfocusedBorderColor = Color(0xFF4A4D5E),
                                        focusedLabelColor = Color(0xFF1BFFFF),
                                        unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color(0xFF1BFFFF)
                                    ),
                                    shape = MaterialTheme.shapes.medium
                                )

                                OutlinedTextField(
                                    value = maxAge,
                                    onValueChange = { maxAge = it },
                                    label = {
                                        Text(
                                            "Edad máxima",
                                            color = Color.White.copy(alpha = 0.7f)
                                        )
                                    },
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFF1BFFFF),
                                        unfocusedBorderColor = Color(0xFF4A4D5E),
                                        focusedLabelColor = Color(0xFF1BFFFF),
                                        unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = Color(0xFF1BFFFF)
                                    ),
                                    shape = MaterialTheme.shapes.medium
                                )
                            }

                            OutlinedTextField(
                                value = dosage,
                                onValueChange = { dosage = it },
                                label = {
                                    Text(
                                        "Dosis recomendada",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2A2D3E)
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "INFORMACIÓN ADICIONAL",
                                color = Color(0xFF1BFFFF),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            OutlinedTextField(
                                value = sideEffects,
                                onValueChange = { sideEffects = it },
                                label = {
                                    Text(
                                        "Efectos secundarios",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )

                            OutlinedTextField(
                                value = contraindications,
                                onValueChange = { contraindications = it },
                                label = {
                                    Text(
                                        "Contraindicaciones",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )

                            OutlinedTextField(
                                value = medicationType,
                                onValueChange = { medicationType = it },
                                label = {
                                    Text(
                                        "Tipo de medicamento",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )

                            OutlinedTextField(
                                value = manufacturer,
                                onValueChange = { manufacturer = it },
                                label = {
                                    Text(
                                        "Fabricante",
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF1BFFFF),
                                    unfocusedBorderColor = Color(0xFF4A4D5E),
                                    focusedLabelColor = Color(0xFF1BFFFF),
                                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    cursorColor = Color(0xFF1BFFFF)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2A2D3E)
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "RECETA MÉDICA",
                                color = Color(0xFF1BFFFF),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "¿Requiere receta médica?",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Switch(
                                    checked = requiresPrescription,
                                    onCheckedChange = { requiresPrescription = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color(0xFF1BFFFF),
                                        checkedTrackColor = Color(0xFF2E3192),
                                        uncheckedThumbColor = Color(0xFF666666),
                                        uncheckedTrackColor = Color(0xFF4A4D5E)
                                    )
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color(0xFF999999)
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(
                            "Cancelar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            if (name.isNotEmpty()) {
                                val newMedicine = Medicine(
                                    id = medicine?.id ?: 0,
                                    name = name,
                                    description = description,
                                    purpose = purpose,
                                    minAge = minAge.toIntOrNull() ?: 0,
                                    maxAge = maxAge.toIntOrNull() ?: 0,
                                    dosage = dosage,
                                    sideEffects = sideEffects,
                                    contraindications = contraindications,
                                    requiresPrescription = requiresPrescription,
                                    medicationType = medicationType,
                                    manufacturer = manufacturer,
                                    createdAt = medicine?.createdAt ?: ""
                                )
                                onSave(newMedicine)
                            }
                        },
                        enabled = name.isNotEmpty(),
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0A0E21),
                            disabledContainerColor = Color(0xFF4A4D5E)
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(
                            "Guardar",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}