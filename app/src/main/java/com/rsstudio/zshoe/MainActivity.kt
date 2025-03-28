package com.rsstudio.zshoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.rsstudio.zshoe.navigation.AppNavGraph
import com.rsstudio.zshoe.navigation.AppNavigationActions
import com.rsstudio.zshoe.ui.theme.ZShoeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ZShoeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ZShoeApp(
                        onFinish = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ZShoeApp(
    onFinish: () -> Unit
) {
    val navController = rememberNavController()
    val navActions = remember(navController) {
        AppNavigationActions(navController, onFinish)
    }
    AppNavGraph(
        navController = navController,
        navActions = navActions
    )
}
