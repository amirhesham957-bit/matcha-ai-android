package com.example.matchaai.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matchaai.theme.*
import com.example.matchaai.ui.components.GlassCard
import com.example.matchaai.ui.components.GlowButton

@Composable
fun VerdictScreen(
    ticker: String,
    onBackHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .padding(24.dp)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FINAL VERDICT",
                style = MaterialTheme.typography.labelLarge,
                color = TextMuted
            )
            Text(
                text = ticker,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Big Verdict Display
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MatchaGreen.copy(alpha = 0.1f))
        ) {
            Text(
                text = "BUY",
                fontSize = 64.sp,
                fontWeight = FontWeight.Black,
                color = MatchaGreen,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Confidence & Risk
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            VerdictMetric(label = "Confidence", value = "87%", color = MatchaGreen)
            VerdictMetric(label = "Risk Level", value = "LOW", color = MatchaGold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Core Thesis
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Core Thesis",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Strong technical breakout aligned with exceptional earnings growth. Council consensus achieved with minor bear concerns regarding valuation multiples.",
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TradeLevel("Entry", "$120.50")
                    TradeLevel("Target", "$145.00")
                    TradeLevel("Stop", "$112.00")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        GlowButton(
            text = "RETURN TO HQ",
            onClick = onBackHome,
            modifier = Modifier.fillMaxWidth(),
            glowColor = BackgroundElevated
        )
    }
}

@Composable
fun VerdictMetric(label: String, value: String, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = TextMuted)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.headlineMedium, color = color)
    }
}

@Composable
fun TradeLevel(label: String, price: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = TextMuted)
        Text(text = price, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}
