package com.example.walktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.walktest.ui.onboarding.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make content draw edge-to-edge (behind status bar)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            OnboardingScreen(
                onJoinClick = { /* TODO: navigate to registration */ },
                onLoginClick = { /* TODO: navigate to login */ }
            )
        }
    }
}
