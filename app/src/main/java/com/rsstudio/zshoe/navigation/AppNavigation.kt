package com.rsstudio.zshoe.navigation

import com.rsstudio.worldt2.common.extension.addRouteArgument


/**
 * Args irrespective of screens are kept here
 */

object AppArgs {
    const val ARG_PRODUCT_ID = "product_id"
}

sealed class AppScreen(val name: String, val route: String) {
    data object ProductScreen : AppScreen("product", "product")
    data object ProductDetailScreen : AppScreen(
        "matchCenter", "matchCenter"
            .addRouteArgument(AppArgs.ARG_PRODUCT_ID)
    )
}