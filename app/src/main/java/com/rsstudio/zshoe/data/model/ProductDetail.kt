package com.rsstudio.zshoe.data.model

import com.rsstudio.zshoe.alias.AppDrawable
import com.rsstudio.zshoe.presentation.product.PopularItem


data class ProductDetail(
    val id: String? = null,
    val name: String? = null,
    val price: String? = null,
    val image: Int? = null,
    val description: String? = null,
    val backgroundColor: String? = null,
    val nameColor: String? = null,
    val priceColor: String? = null,
    val sizeInfo: List<SizeInfo>? = null,
    val productDesign: List<Int>? = null,
)


data class SizeInfo(
    val id: String = "",
    val name: String = "",
    val isPresent: Boolean = false
)

fun List<ProductDetail>.toProductItem(): List<PopularItem> {
    return this.map {
        PopularItem(
            id = it.id.orEmpty(),
            name = it.name.orEmpty(),
            price = it.price.orEmpty(),
            image = it.image ?: AppDrawable.ic_red_shoe,
            backgroundColor = it.backgroundColor.orEmpty(),
            nameColor = it.nameColor.orEmpty(),
            priceColor = it.priceColor.orEmpty()
        )
    }
}