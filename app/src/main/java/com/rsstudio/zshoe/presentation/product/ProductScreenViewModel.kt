package com.rsstudio.zshoe.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsstudio.zshoe.alias.AppDrawable
import com.rsstudio.zshoe.domain.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ProductScreenUiState())
        private set

    var uiSideEffect by mutableStateOf<ProductScreenSideEffects>(ProductScreenSideEffects.NoEffect)
        private set


    init {
        getPopularItemData()
    }

    private fun getPopularItemData() {
        viewModelScope.launch {
            uiState = uiState.copy(
                popularList = getProductDetailUseCase(),
                categoriesList = listOf(
                    "All",
                    "Air Max",
                    "Presto",
                    "Huarache",
                    "Mercurial"
                ),
                otherList = listOf(
                    OtherItem(
                        id = "1",
                        name = "Undercover React Presto",
                        price = "₹12,797",
                        image = AppDrawable.ic_blue_pegasus
                    ), OtherItem(
                        id = "2",
                        name = "Air Zoom Pegasus 37",
                        price = "₹9,995",
                        image = AppDrawable.ic_yellow_shoe
                    ),
                    OtherItem(
                        id = "3",
                        name = "Air Presto by you",
                        price = "₹12,797",
                        image = AppDrawable.ic_white_blue_shoe
                    ),
                    OtherItem(
                        id = "4",
                        name = "KD13 EP",
                        price = "₹12,797",
                        image = AppDrawable.ic_green_shoe
                    )
                )
            )
        }
    }

    fun onEvent(event: ProductScreenEvent) {
        when (event) {
            is ProductScreenEvent.OnClickPopularItem -> {
                uiSideEffect = ProductScreenSideEffects.OpenProductDetailScreen(
                    event.popularItem.id,
                    "image${event.popularItem.id}-${event.popularItem.image}"
                )
            }

            is ProductScreenEvent.OnClickCategory -> {
                uiState = uiState.copy(selectedCategory = event.category)
            }
        }
    }

    fun resetUiSideEffect() {
        uiSideEffect = ProductScreenSideEffects.NoEffect
    }
}

data class ProductScreenUiState(
    val popularList: List<PopularItem> = emptyList(),
    val otherList: List<OtherItem> = emptyList(),
    val categoriesList: List<String> = emptyList(),
    val selectedCategory: String = "All"
)

data class PopularItem(
    val id: String,
    val name: String = "",
    val price: String = "",
    val image: Int = AppDrawable.ic_red_shoe,
    val backgroundColor: String = "",
    val nameColor: String = "",
    val priceColor: String = "",
) {
    fun getBackgroundId() = "backgroundId-$id$backgroundColor"
}


data class OtherItem(
    val id: String,
    val name: String = "",
    val price: String = "",
    val image: Int = AppDrawable.ic_red_shoe,
)

sealed interface ProductScreenEvent {
    data class OnClickPopularItem(val popularItem: PopularItem) : ProductScreenEvent
    data class OnClickCategory(val category: String) : ProductScreenEvent
}


sealed interface ProductScreenSideEffects {
    data object NoEffect : ProductScreenSideEffects
    data class OpenProductDetailScreen(val productId: String, val imageId: String) :
        ProductScreenSideEffects
}