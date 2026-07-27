package com.example.matchaai

import androidx.compose.animation.*
import androidx.compose.runtime.*
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

    Crossfade(targetState = currentScreen, label = "screen_transition") { screen ->
        when (screen) {
            Screen.Dashboard -> {
                DashboardScreen(
                    onAnalyzeClick = { ticker ->
                        currentTicker = ticker
                        currentScreen = Screen.Analysis
                    }
                )
            }
            Screen.Analysis -> {
                AnalysisScreen(
                    ticker = currentTicker,
                    onAnalysisComplete = {
                        currentScreen = Screen.Verdict
                    }
                )
            }
            Screen.Verdict -> {
                VerdictScreen(
                    ticker = currentTicker,
                    onBackHome = {
                        currentTicker = ""
                        currentScreen = Screen.Dashboard
                    }
                )
            }
        }
    }
}
