package com.rsstudio.zshoe.presentation.product

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rsstudio.worldt2.common.extension.noRippleClickable
import com.rsstudio.zshoe.alias.AppDrawable
import com.rsstudio.zshoe.alias.AppString
import com.rsstudio.zshoe.components.chips.Chip
import com.rsstudio.zshoe.navigation.actions.ProductScreenActions
import com.rsstudio.zshoe.ui.theme.blue
import com.rsstudio.zshoe.ui.theme.ghostGray
import com.rsstudio.zshoe.ui.theme.ht1
import com.rsstudio.zshoe.ui.theme.label
import com.rsstudio.zshoe.ui.theme.lightBlack
import com.rsstudio.zshoe.ui.theme.pebbleMist
import com.rsstudio.zshoe.ui.theme.subTitle
import com.rsstudio.zshoe.ui.theme.white
import kotlin.math.abs


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.ProductScreen(
    onAction: (ProductScreenActions) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    boundsTransform: BoundsTransform,
    viewModel: ProductScreenViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = white
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = AppDrawable.ic_arrow),
                        contentDescription = "backArrow"
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(id = AppDrawable.ic_search),
                        contentDescription = "backArrow"
                    )
                }
            )
        }
    ) {
        ProductScreenContent(
            modifier = Modifier.padding(it),
            uiState = viewModel.uiState,
            animatedVisibilityScope = animatedVisibilityScope,
            boundsTransform = boundsTransform,
            onEvent = viewModel::onEvent
        )
    }

    LaunchedEffect(key1 = viewModel.uiSideEffect) {
        handleSideEffects(
            effect = viewModel.uiSideEffect,
            onAction = onAction,
        )
        viewModel.resetUiSideEffect()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProductScreenUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    boundsTransform: BoundsTransform,
    onEvent: (ProductScreenEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {

        item {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = AppString.shoes),
                style = MaterialTheme.typography.ht1.copy(
                    fontSize = 26.sp,
                    color = lightBlack
                )
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
            ) {
                items(
                    items = uiState.categoriesList
                ) {
                    Chip(
                        text = it,
                        selected = uiState.selectedCategory == it
                    ) {
                        onEvent(ProductScreenEvent.OnClickCategory(it))
                    }
                }
            }
        }

        item {
            val pagerState = rememberPagerState(
                pageCount = { uiState.popularList.size },
                initialPage = 0
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .height(304.dp),
                contentPadding = PaddingValues(start = 16.dp, end = 60.dp),
                pageSpacing = 42.dp,
                pageSize = PageSize.Fixed(256.dp)
            ) { page ->
                val pageOffset by remember {
                    derivedStateOf {
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).coerceIn(
                            -1f,
                            1f
                        )
                    }
                }

                val rotation = when (page) {
                    0 -> {
                        lerp(
                            start = -30f,
                            stop = 0f,
                            fraction = abs(pageOffset).coerceIn(0f, 1f)
                        )
                    }

                    else -> {
                        when {
                            pageOffset < 0 -> {
                                -40f
                            }

                            pageOffset == 0f -> {
                                -30f
                            }

                            else -> {
                                lerp(
                                    start = -30f,
                                    stop = 0f,
                                    fraction = abs(pageOffset).coerceIn(0f, 1f)
                                )
                            }
                        }
                    }
                }
                PopularCategoryItem(
                    item = uiState.popularList[page],
                    rotate = rotation,
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = boundsTransform,
                    onClickPopularItem = {
                        onEvent(ProductScreenEvent.OnClickPopularItem(it))
                    }
                )
            }
        }

        item {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 48.dp),
                text = stringResource(id = AppString.total_options, uiState.otherList.size),
                style = MaterialTheme.typography.label.copy(
                    fontSize = 12.sp,
                    color = lightBlack
                )
            )
        }

        itemsIndexed(uiState.otherList) { index, item ->
            OtherItem(
                item = item,
                showDivider = index != uiState.otherList.size - 1
            )
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PopularCategoryItem(
    item: PopularItem,
    rotate: Float = 30f,
    modifier: Modifier = Modifier,
    boundsTransform: BoundsTransform,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClickPopularItem: (PopularItem) -> Unit
) {
    Column(
        modifier = modifier
            .width(256.dp)
            .height(304.dp)
            .background(
                Color(item.backgroundColor.toLong(16)),
                RoundedCornerShape(16.dp)
            )
            .padding(top = 32.dp)
            .noRippleClickable {
                onClickPopularItem(item)
            }
            .sharedElement(
                state = rememberSharedContentState(
                    key = item.getBackgroundId()
                ),
                animatedVisibilityScope = animatedVisibilityScope
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.ht1.copy(
                    fontSize = 24.sp,
                    color = Color(item.nameColor.toLong(16)),
                )
            )
            Text(
                text = item.price,
                style = MaterialTheme.typography.label.copy(
                    fontSize = 18.sp,
                    color = Color(item.priceColor.toLong(16)),
                )
            )
        }

        Box {
            Divider(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .height(168.dp)
                    .width(1.dp)
                    .align(Alignment.TopStart),
                color = Color(item.priceColor.toLong(16)),
            )
            Image(
                modifier = Modifier
                    .width(252.dp)
                    .height(141.dp)
                    .offset(x = 12.dp)
                    .rotate(rotate)
                    .sharedElement(
                        state = rememberSharedContentState(
                            key = "image${item.id}-${item.image}"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = boundsTransform
                    ),
                painter = painterResource(id = item.image),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

    }
}


@Composable
fun OtherItem(
    item: OtherItem,
    showDivider: Boolean = true
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .width(121.dp)
                    .height(54.dp),
                painter = painterResource(id = item.image),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(24.dp))

            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.subTitle.copy(
                        fontSize = 16.sp,
                        color = lightBlack
                    )
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = item.price,
                    style = MaterialTheme.typography.label.copy(
                        fontSize = 14.sp
                    )
                )
            }
        }

        if (showDivider) {
            Spacer(modifier = Modifier.size(12.dp))
            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(18.dp),
                        color = ghostGray
                    ),
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

private fun handleSideEffects(
    effect: ProductScreenSideEffects,
    onAction: (actions: ProductScreenActions) -> Unit,
) {
    when (effect) {
        ProductScreenSideEffects.NoEffect -> {

        }

        is ProductScreenSideEffects.OpenProductDetailScreen -> {
            onAction(ProductScreenActions.OpenProductDetailScreen(effect.productId, effect.imageId))
        }
    }
}

