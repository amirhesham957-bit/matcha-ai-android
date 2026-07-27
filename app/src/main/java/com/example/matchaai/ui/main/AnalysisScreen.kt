package com.example.matchaai.ui.main

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.matchaai.theme.*
import com.example.matchaai.ui.components.GlassCard
import kotlinx.coroutines.delay

@Composable
fun AnalysisScreen(
    ticker: String,
    onAnalysisComplete: () -> Unit
) {
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        // Simulate pipeline stages
        while (progress < 1f) {
            delay(500)
            progress += 0.1f
            if (progress >= 1f) {
                delay(1000)
                onAnalysisComplete()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .padding(24.dp)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WAR ROOM ACTIVE",
            style = MaterialTheme.typography.labelLarge,
            color = MatchaGold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = ticker,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Scouts Progress
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AgentStatusCard("Price Scout 📈", progress > 0.2f, progress > 0.4f)
            AgentStatusCard("Fundamentals Scout 📊", progress > 0.4f, progress > 0.6f)
            AgentStatusCard("Sentiment Scout 📰", progress > 0.6f, progress > 0.8f)
            AgentStatusCard("Macro Scout 🌍", progress > 0.8f, progress > 0.9f)
        }

        Spacer(modifier = Modifier.weight(1f))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = MatchaGreen,
            trackColor = BackgroundElevated
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Assembling intelligence...",
            style = MaterialTheme.typography.labelMedium,
            color = TextMuted
        )
    }
}

@Composable
fun AgentStatusCard(name: String, isActive: Boolean, isComplete: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = if (isActive || isComplete) TextPrimary else TextMuted
            )

            if (isComplete) {
                Text("✅", style = MaterialTheme.typography.titleMedium)
            } else if (isActive) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(MatchaGreen.copy(alpha = alpha))
                )
            }
        }
    }
}
