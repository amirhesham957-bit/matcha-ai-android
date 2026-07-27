package com.example.matchaai

import androidx.compose.animation.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import com.example.matchaai.ui.main.AnalysisScreen
import com.example.matchaai.ui.main.DashboardScreen
import com.example.matchaai.ui.main.VerdictScreen

enum class Screen {
    Dashboard, Analysis, Verdict
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(Screen.Dashboard) }
    var currentTicker by remember { mutableStateOf("") }
    var analysisVerdict by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Crossfade(targetState = currentScreen, label = "screen_transition") { screen ->
        when (screen) {
            Screen.Dashboard -> {
                DashboardScreen(
                    onAnalyzeClick = { ticker ->
                        currentTicker = ticker
                        currentScreen = Screen.Analysis
                        
                        // Launch network request in background
                        coroutineScope.launch {
                            val result = com.example.matchaai.data.ApiClient.analyzeTicker(ticker)
                            if (result != null && result.status == "success") {
                                analysisVerdict = result.verdict ?: "No clear verdict provided."
                            } else {
                                analysisVerdict = "Failed to retrieve analysis from War Room."
                            }
                            currentScreen = Screen.Verdict
                        }
                    }
                )
            }
            Screen.Analysis -> {
                // We don't trigger the transition here anymore, it's triggered by the coroutine above.
                AnalysisScreen(
                    ticker = currentTicker,
                    onAnalysisComplete = {
                        // Keep this parameter for the UI but we won't use it to transition
                    }
                )
            }
            Screen.Verdict -> {
                VerdictScreen(
                    ticker = currentTicker,
                    verdictText = analysisVerdict,
                    onBackHome = {
                        currentTicker = ""
                        analysisVerdict = ""
                        currentScreen = Screen.Dashboard
                    }
                )
            }
        }
    }
}
