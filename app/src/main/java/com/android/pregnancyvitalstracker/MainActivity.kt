package com.android.pregnancyvitalstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
