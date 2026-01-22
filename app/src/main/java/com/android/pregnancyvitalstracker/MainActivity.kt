package com.android.pregnancyvitalstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.pregnancyvitalstracker.ui.home.HomeScreen
import com.android.pregnancyvitalstracker.ui.theme.PregnancyVitalsTrackerTheme
import com.android.pregnancyvitalstracker.util.scheduleVitalsReminder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleVitalsReminder(this)

        enableEdgeToEdge()
        setContent {
            PregnancyVitalsTrackerTheme {
                HomeScreen()
            }
        }
    }
}
