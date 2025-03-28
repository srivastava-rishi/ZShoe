package com.rsstudio.zshoe.data.repositories

import com.rsstudio.zshoe.alias.AppDrawable
import com.rsstudio.zshoe.data.model.ProductDetail
import com.rsstudio.zshoe.data.model.SizeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductDetailRepository @Inject constructor() {

    fun getProductDetail(): Flow<List<ProductDetail>> = flow {
        emit(
            listOf(
                ProductDetail(
                    id = "1",
                    name = "Alpha Savage",
                    price = "₹8,895",
                    image = AppDrawable.ic_red_shoe,
                    backgroundColor = "FFE24C4D",
                    nameColor = "FFFFFFFF",
                    priceColor = "FFF6C9CA",
                    description = "In the game's crucial moments, KD thrives. He takes over on both ends of the court, making defenders fear his unstopp... MORE",
                    sizeInfo = listOf(
                        SizeInfo(
                            "1",
                            "UK 6",
                            true
                        ),
                        SizeInfo(
                            "2",
                            "UK 7",
                            true
                        ),
                        SizeInfo(
                            "3",
                            "UK 8",
                            true
                        ),
                        SizeInfo(
                            "4",
                            "UK 9",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 10",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 11",
                            false
                        ),
                        SizeInfo(
                            "6",
                            "UK 12",
                            true
                        ),
                        SizeInfo(
                            "6",
                            "UK 13",
                            false
                        )
                    ),
                    productDesign = listOf(
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                    )
                ),
                ProductDetail(
                    id = "2",
                    name = "Air Max 97",
                    price = "₹11,897",
                    image = AppDrawable.ic_yellow_shoe,
                    backgroundColor = "FFFDBA62",
                    nameColor = "FF1F2732",
                    priceColor = "FF625341",
                    description = "In the game's crucial moments, KD thrives. He takes over on both ends of the court, making defenders fear his unstopp... MORE",
                    sizeInfo = listOf(
                        SizeInfo(
                            "1",
                            "UK 6",
                            true
                        ),
                        SizeInfo(
                            "2",
                            "UK 7",
                            true
                        ),
                        SizeInfo(
                            "3",
                            "UK 8",
                            true
                        ),
                        SizeInfo(
                            "4",
                            "UK 9",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 10",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 11",
                            false
                        ),
                        SizeInfo(
                            "6",
                            "UK 12",
                            true
                        ),
                        SizeInfo(
                            "6",
                            "UK 13",
                            false
                        )

                    ),
                    productDesign = listOf(
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                    )
                ),
                ProductDetail(
                    id = "3",
                    name = "KD13 EP",
                    price = "₹12,995",
                    image = AppDrawable.ic_white_blue_shoe,
                    backgroundColor = "FF4B81F4",
                    nameColor = "FFFFFFFF",
                    priceColor = "FFF6C9CA",
                    description = "In the game's crucial moments, KD thrives. He takes over on both ends of the court, making defenders fear his unstopp... MORE",
                    sizeInfo = listOf(
                        SizeInfo(
                            "1",
                            "UK 6",
                            true
                        ),
                        SizeInfo(
                            "2",
                            "UK 7",
                            true
                        ),
                        SizeInfo(
                            "3",
                            "UK 8",
                            true
                        ),
                        SizeInfo(
                            "4",
                            "UK 9",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 10",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 11",
                            false
                        ),
                        SizeInfo(
                            "6",
                            "UK 12",
                            true
                        ),
                        SizeInfo(
                            "6",
                            "UK 13",
                            false
                        )
                    ),
                    productDesign = listOf(
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                    )
                ),
                ProductDetail(
                    id = "4",
                    name = "Air Presto by you ",
                    price = "₹29,995",
                    image = AppDrawable.ic_green_shoe,
                    backgroundColor = "FF599C99",
                    nameColor = "FFFFFFFF",
                    priceColor = "FFF6C9CA",
                    description = "In the game's crucial moments, KD thrives. He takes over on both ends of the court, making defenders fear his unstopp... MORE",
                    sizeInfo = listOf(
                        SizeInfo(
                            "1",
                            "UK 6",
                            true
                        ),
                        SizeInfo(
                            "2",
                            "UK 7",
                            true
                        ),
                        SizeInfo(
                            "3",
                            "UK 8",
                            true
                        ),
                        SizeInfo(
                            "4",
                            "UK 9",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 10",
                            true
                        ),
                        SizeInfo(
                            "5",
                            "UK 11",
                            false
                        ),
                        SizeInfo(
                            "6",
                            "UK 12",
                            true
                        ),
                        SizeInfo(
                            "6",
                            "UK 13",
                            false
                        )
                    ),
                    productDesign = listOf(
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                        AppDrawable.ic_yellow_shoe,
                    )
                )
            )
        )
    }.flowOn(Dispatchers.IO)
}