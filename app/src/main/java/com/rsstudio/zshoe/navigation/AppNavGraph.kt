package com.rsstudio.zshoe.navigation

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rsstudio.zshoe.presentation.detail.ProductDetailScreen
import com.rsstudio.zshoe.presentation.product.ProductScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AppScreen.ProductScreen.route,
    navActions: AppNavigationActions
) {
    SharedTransitionLayout {
        val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
            keyframes {
                durationMillis = 600
                initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                targetBounds at 600
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable(AppScreen.ProductScreen.route) {
                ProductScreen(
                    animatedVisibilityScope = this,
                    boundsTransform = boundsTransform,
                    onAction = navActions::navigateFromProductScreen
                )
            }
            composable(
                route = AppScreen.ProductDetailScreen.route,
                arguments = listOf(
                    navArgument(AppArgs.ARG_PRODUCT_ID) { type = NavType.StringType }
                )
            ) {
                ProductDetailScreen(
                    onAction = navActions::navigateFromProductDetailScreen,
                    animatedVisibilityScope = this,
                    boundsTransform = boundsTransform,
                )
            }
        }
    }
}