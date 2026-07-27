package com.example.matchaai.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.matchaai.theme.*
import com.example.matchaai.ui.components.GlassCard
import com.example.matchaai.ui.components.GlowButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    isAnalyzing: Boolean,
    recentIntel: List<com.example.matchaai.data.RecentIntel>,
    onAnalyzeClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .padding(24.dp)
            .systemBarsPadding()
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "WAR ROOM",
                    style = MaterialTheme.typography.labelLarge,
                    color = MatchaGreen
                )
                Text(
                    text = "Matcha AI",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(BackgroundElevated),
                contentAlignment = Alignment.Center
            ) {
                Text("🍵", style = MaterialTheme.typography.headlineMedium)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Search Bar
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it.uppercase() },
                placeholder = { Text("Enter Ticker (e.g., NVDA)", color = TextMuted) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = MatchaGreen)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = MatchaGreen,
                    focusedTextColor = TextPrimary
                ),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isAnalyzing) {
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MatchaGreen)
            }
        } else {
            GlowButton(
                text = "ANALYZE NOW",
                onClick = { if (searchQuery.isNotBlank()) onAnalyzeClick(searchQuery) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Recent Intelligence",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (recentIntel.isEmpty()) {
            Text("No recent intelligence found.", color = TextMuted)
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recentIntel.size) { index ->
                    val intel = recentIntel[index]
                    val color = when (intel.decision) {
                        "BUY" -> SignalBuy
                        "SELL" -> SignalSell
                        else -> SignalHold
                    }

                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = intel.ticker,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Confidence: ${intel.confidence}%",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = TextMuted
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(color.copy(alpha = 0.2f))
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = intel.decision,
                                    color = color,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
