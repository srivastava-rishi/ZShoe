package com.rsstudio.zshoe.navigation

import androidx.navigation.NavController
import com.rsstudio.worldt2.common.extension.withArg
import com.rsstudio.zshoe.navigation.actions.ProductDetailScreenActions
import com.rsstudio.zshoe.navigation.actions.ProductScreenActions

class AppNavigationActions(
    private val navController: NavController,
    private val onFinish: () -> Unit
) {

    private fun back() {
        navController.popBackStack()
    }

    private fun finishActivity() {
        onFinish()
    }

    fun navigateFromProductScreen(actions: ProductScreenActions) {
        when (actions) {
            is ProductScreenActions.OpenProductDetailScreen -> {
                navController.navigate(
                    AppScreen.ProductDetailScreen.name
                        .withArg(AppArgs.ARG_PRODUCT_ID, actions.productId)
                )
            }
        }
    }

    fun navigateFromProductDetailScreen(actions: ProductDetailScreenActions) {
        when (actions) {
            ProductDetailScreenActions.OnBack -> {
                back()
            }
        }
    }
}