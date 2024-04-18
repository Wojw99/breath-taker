package com.example.breathtaker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.breathtaker.presentation.article.ArticleDetailsScreen
import com.example.breathtaker.presentation.breath.BreathScreen
import com.example.breathtaker.presentation.main.MainScreen
import com.example.breathtaker.presentation.mood.MoodScreen
import com.example.breathtaker.presentation.ui.theme.BreathTakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreathTakerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                        composable(Screen.ArticleDetailsScreen.route) {
                            ArticleDetailsScreen(navController = navController)
                        }
                        composable(Screen.BreathScreen.route) {
                            BreathScreen(navController = navController)
                        }
                        composable(Screen.MoodScreen.route) {
                            MoodScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BreathTakerTheme {
        Greeting("Android")
    }
}