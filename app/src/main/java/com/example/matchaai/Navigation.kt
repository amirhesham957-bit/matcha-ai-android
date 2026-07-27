package com.example.matchaai

import androidx.compose.animation.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import com.example.matchaai.ui.main.AnalysisScreen
import com.example.matchaai.ui.main.DashboardScreen
import com.example.matchaai.ui.main.VerdictScreen

enum class Screen {
    Dashboard, Verdict
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(Screen.Dashboard) }
    var currentTicker by remember { mutableStateOf("") }
    
    var analysisVerdict by remember { mutableStateOf("") }
    var analysisDecision by remember { mutableStateOf("HOLD") }
    var analysisConfidence by remember { mutableStateOf(50) }
    var analysisReasoning by remember { mutableStateOf("") }
    
    var isAnalyzing by remember { mutableStateOf(false) }
    var recentIntelList by remember { mutableStateOf<List<com.example.matchaai.data.RecentIntel>>(emptyList()) }
    
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val result = com.example.matchaai.data.ApiClient.getRecentIntelligence()
        if (result != null && result.status == "success" && result.data != null) {
            recentIntelList = result.data
        }
    }

    Crossfade(targetState = currentScreen, label = "screen_transition") { screen ->
        when (screen) {
            Screen.Dashboard -> {
                DashboardScreen(
                    isAnalyzing = isAnalyzing,
                    recentIntel = recentIntelList,
                    onAnalyzeClick = { ticker ->
                        currentTicker = ticker
                        isAnalyzing = true
                        
                        coroutineScope.launch {
                            val result = com.example.matchaai.data.ApiClient.analyzeTicker(ticker)
                            isAnalyzing = false
                            if (result != null && result.status == "success") {
                                analysisVerdict = result.verdict ?: "No clear verdict provided."
                                analysisDecision = result.decision ?: "HOLD"
                                analysisConfidence = result.confidence ?: 50
                                analysisReasoning = result.reasoning ?: "Detailed reasoning is available in the War Room log."
                            } else {
                                analysisVerdict = "Failed to retrieve analysis from War Room."
                                analysisDecision = "ERROR"
                                analysisConfidence = 0
                                analysisReasoning = "Connection failed."
                            }
                            currentScreen = Screen.Verdict
                        }
                    }
                )
            }
            Screen.Verdict -> {
                VerdictScreen(
                    ticker = currentTicker,
                    decision = analysisDecision,
                    confidence = analysisConfidence,
                    reasoning = analysisReasoning,
                    onBackHome = {
                        currentTicker = ""
                        currentScreen = Screen.Dashboard
                        // Refresh recent list
                        coroutineScope.launch {
                            val result = com.example.matchaai.data.ApiClient.getRecentIntelligence()
                            if (result != null && result.status == "success" && result.data != null) {
                                recentIntelList = result.data
                            }
                        }
                    }
                )
            }
        }
    }
}
