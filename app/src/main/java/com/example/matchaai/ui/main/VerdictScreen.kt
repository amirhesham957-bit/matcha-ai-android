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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matchaai.theme.*
import com.example.matchaai.ui.components.GlassCard
import com.example.matchaai.ui.components.GlowButton
import com.example.matchaai.ui.components.TradingViewChart

@Composable
fun VerdictScreen(
    ticker: String,
    decision: String,
    confidence: Int,
    reasoning: String,
    onBackHome: () -> Unit
) {
    val decisionColor = when (decision.uppercase()) {
        "BUY" -> SignalBuy
        "SELL" -> SignalSell
        else -> SignalHold
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
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

        Spacer(modifier = Modifier.height(16.dp))

        // TradingView Chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundElevated)
        ) {
            TradingViewChart(ticker = ticker)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Decision and Confidence
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(decisionColor.copy(alpha = 0.1f))
            ) {
                Text(
                    text = decision.uppercase(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = decisionColor,
                    letterSpacing = 1.sp
                )
            }
            VerdictMetric(label = "Confidence", value = "$confidence%", color = decisionColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Core Thesis
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Core Thesis",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reasoning,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp,
                    maxLines = 5
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
