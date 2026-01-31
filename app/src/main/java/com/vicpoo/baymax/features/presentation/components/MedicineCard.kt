package com.vicpoo.baymax.features.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicpoo.baymax.features.domain.model.Medicine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCard(
    medicine: Medicine,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                spotColor = Color(0xFF1BFFFF).copy(alpha = 0.3f),
                ambientColor = Color(0xFF2E3192).copy(alpha = 0.1f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2D3E)
        ),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF2E3192),
                                Color(0xFF1BFFFF)
                            )
                        )
                    )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = medicine.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    if (medicine.description.isNotEmpty()) {
                        Text(
                            text = medicine.description,
                            fontSize = 14.sp,
                            color = Color(0xFFCCCCCC),
                            maxLines = 2,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (medicine.medicationType.isNotEmpty()) {
                            Surface(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .shadow(2.dp),
                                color = Color(0xFF1C1F2E),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Text(
                                    text = medicine.medicationType,
                                    color = Color(0xFF1BFFFF),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        if (medicine.manufacturer.isNotEmpty()) {
                            Surface(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .shadow(2.dp),
                                color = Color(0xFF1C1F2E),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Text(
                                    text = "🏭 ${medicine.manufacturer}",
                                    color = Color(0xFFAAAAAA),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .shadow(4.dp),
                        color = if (medicine.requiresPrescription) {
                            Color(0xFFD32F2F).copy(alpha = 0.2f)
                        } else {
                            Color(0xFF4CAF50).copy(alpha = 0.2f)
                        },
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (medicine.requiresPrescription) Color(0xFFD32F2F)
                                        else Color(0xFF4CAF50)
                                    )
                            )
                            Text(
                                text = if (medicine.requiresPrescription) "Con receta"
                                else "Sin receta",
                                color = if (medicine.requiresPrescription) Color(0xFFFF5252)
                                else Color(0xFF69F0AE),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onEditClick,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(MaterialTheme.shapes.medium)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.medium)
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
                                Icons.Default.Edit,
                                contentDescription = "Editar",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                        }
                    }

                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(MaterialTheme.shapes.medium)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.medium)
                                .background(Color(0xFFD32F2F)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            if (medicine.purpose.isNotEmpty() || medicine.dosage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    color = Color(0xFF1C1F2E).copy(alpha = 0.5f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        if (medicine.purpose.isNotEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "🎯",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = medicine.purpose,
                                    color = Color(0xFFAAAAAA),
                                    fontSize = 13.sp,
                                    maxLines = 1
                                )
                            }
                        }

                        if (medicine.dosage.isNotEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "⚖️",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = medicine.dosage,
                                    color = Color(0xFFAAAAAA),
                                    fontSize = 13.sp,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}