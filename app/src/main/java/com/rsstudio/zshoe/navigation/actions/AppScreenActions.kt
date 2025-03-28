package com.rsstudio.zshoe.navigation.actions

/**
 * Add all you app screen actions here ====================================
 */


sealed class ProductScreenActions {
    data class OpenProductDetailScreen(
        val productId: String,
        val imageId: String
    ) : ProductScreenActions()
}

sealed class ProductDetailScreenActions {
    data object OnBack : ProductDetailScreenActions()
}

