package com.rsstudio.zshoe.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rsstudio.zshoe.data.model.SizeInfo
import com.rsstudio.zshoe.domain.GetProductDetailUseCase
import com.rsstudio.zshoe.navigation.AppArgs
import com.rsstudio.zshoe.presentation.product.ProductScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProductDetailScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ProductDetailScreenUiState())
        private set

    var uiSideEffect by mutableStateOf<ProductDetailScreenSideEffects>(
        ProductDetailScreenSideEffects.NoEffect
    )
        private set

    private val productId: String = savedStateHandle[AppArgs.ARG_PRODUCT_ID] ?: ""

    init {
        fetchProductDetail(productId)
    }

    private fun fetchProductDetail(productId: String) {
        val data = getProductDetailUseCase.getProductById(productId)
        uiState = uiState.copy(
            imageId = "image${data?.id}-${data?.image}",
            backgroundId = "backgroundId-${data?.id}${data?.backgroundColor}",
            productInfo = ProductInfo(
                name = data?.name ?: "",
                price = data?.price ?: "",
                description = data?.description ?: "",
                image = data?.image,
                background = data?.backgroundColor ?: ""
            ),
            sizeList = data?.sizeInfo ?: emptyList()
        )
    }

    fun onEvent(event: ProductDetailScreenEvent) {
        when (event) {
            is ProductDetailScreenEvent.OnBack -> {
                uiSideEffect = ProductDetailScreenSideEffects.Back
            }

            is ProductDetailScreenEvent.OnSelectSize -> {
                Log.d("lion22", "onEvent: ${event.size}")
                uiState = uiState.copy(selectedSize = event.size)
            }
        }
    }

    fun resetUiSideEffect() {
        uiSideEffect = ProductDetailScreenSideEffects.NoEffect
    }
}

data class ProductDetailScreenUiState(
    val productInfo: ProductInfo = ProductInfo(),
    val sizeList: List<SizeInfo> = emptyList(),
    val selectedSize: String = "UK 10",
    val imageId: String = "",
    val backgroundId: String = "",
)

data class ProductInfo(
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val image: Int? = null,
    val background: String = "",
)

sealed interface ProductDetailScreenEvent {
    data object OnBack : ProductDetailScreenEvent
    data class OnSelectSize(val size: String) : ProductDetailScreenEvent
}


sealed interface ProductDetailScreenSideEffects {
    data object NoEffect : ProductDetailScreenSideEffects
    data object Back : ProductDetailScreenSideEffects
}
