package com.rsstudio.zshoe.domain

import com.rsstudio.zshoe.data.model.ProductDetail
import com.rsstudio.zshoe.data.model.toProductItem
import com.rsstudio.zshoe.data.repositories.ProductDetailRepository
import com.rsstudio.zshoe.presentation.product.PopularItem
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private var productDetailRepository: ProductDetailRepository
) {

    private var list = emptyList<ProductDetail>()

    suspend operator fun invoke(): List<PopularItem> {
        list = productDetailRepository.getProductDetail().firstOrNull()
            ?: emptyList()
        return list.toProductItem()
    }

    fun getProductById(id: String) = list.find { it.id == id }
}