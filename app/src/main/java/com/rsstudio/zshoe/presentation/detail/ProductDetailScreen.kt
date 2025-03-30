package com.rsstudio.zshoe.presentation.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rsstudio.worldt2.common.extension.noRippleClickable
import com.rsstudio.zshoe.alias.AppDrawable
import com.rsstudio.zshoe.alias.AppString
import com.rsstudio.zshoe.data.model.SizeInfo
import com.rsstudio.zshoe.navigation.actions.ProductDetailScreenActions
import com.rsstudio.zshoe.ui.theme.grey
import com.rsstudio.zshoe.ui.theme.grey56
import com.rsstudio.zshoe.ui.theme.ht1
import com.rsstudio.zshoe.ui.theme.label
import com.rsstudio.zshoe.ui.theme.labelSmall2
import com.rsstudio.zshoe.ui.theme.lightBlack
import com.rsstudio.zshoe.ui.theme.slateGrey
import com.rsstudio.zshoe.ui.theme.subTitle
import com.rsstudio.zshoe.ui.theme.white
import com.rsstudio.zshoe.ui.theme.white22

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductDetailScreen(
    onAction: (ProductDetailScreenActions) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    boundsTransform: BoundsTransform,
    viewModel: ProductDetailScreenViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(viewModel.uiState.productInfo.background.toLong(16))
        )
    }

    ProductDetailContent(
        uiState = viewModel.uiState,
        animatedVisibilityScope = animatedVisibilityScope,
        boundsTransform = boundsTransform,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(key1 = viewModel.uiSideEffect) {
        handleSideEffects(
            effect = viewModel.uiSideEffect,
            onAction = onAction,
        )
        viewModel.resetUiSideEffect()
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductDetailContent(
    uiState: ProductDetailScreenUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    boundsTransform: BoundsTransform,
    onEvent: (ProductDetailScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            Box(
                modifier = Modifier.wrapContentWidth()
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedBounds(
                            rememberSharedContentState(key = uiState.backgroundId),
                            animatedVisibilityScope = animatedVisibilityScope,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                        ),
                    painter = painterResource(id = AppDrawable.ic_arc),
                    contentDescription = "",
                    tint = Color(uiState.productInfo.background.toLong(16))
                )

                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        Row {
                            Spacer(modifier = Modifier.size(16.dp))
                            Icon(
                                modifier = Modifier.noRippleClickable {
                                    onEvent(ProductDetailScreenEvent.OnBack)
                                },
                                painter = painterResource(id = AppDrawable.ic_arrow),
                                contentDescription = "backArrow",
                                tint = white
                            )
                        }
                    },
                    actions = {
                        Icon(
                            painter = painterResource(id = AppDrawable.ic_heart),
                            contentDescription = "heart",
                            tint = white
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    uiState.productInfo.image?.let {
                        Image(
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(top = 80.dp)
                                .width(290.dp)
                                .height(160.dp)
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(
                                        key = uiState.imageId
                                    ),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = boundsTransform
                                ),
                            painter = painterResource(id = it),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiState.productInfo.name,
                    style = MaterialTheme.typography.ht1.copy(
                        fontSize = 26.sp,
                        color = lightBlack
                    )
                )
                Text(
                    text = uiState.productInfo.price,
                    style = MaterialTheme.typography.subTitle.copy(
                        color = lightBlack
                    )
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = uiState.productInfo.description,
                style = MaterialTheme.typography.labelSmall2.copy(
                    fontSize = 16.sp,
                    color = grey56
                )
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = uiState.productInfo.description,
                style = MaterialTheme.typography.labelSmall2.copy(
                    fontSize = 16.sp,
                    color = grey56
                )
            )
            Spacer(modifier = Modifier.size(24.dp))
            SelectShoeSection()
            SelectSizeSection(
                list = uiState.sizeList,
                selectedSize = uiState.selectedSize,
                onSelectSize = {
                    onEvent(ProductDetailScreenEvent.OnSelectSize(it))
                }
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(48.dp)
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = lightBlack
            ),
            shape = RoundedCornerShape(12.dp),
            onClick = {
            }
        ) {
            Text(
                text = stringResource(id = AppString.add_to_bag)
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectSizeSection(
    selectedSize: String,
    list: List<SizeInfo>,
    onSelectSize: (String) -> Unit
) {
    Spacer(modifier = Modifier.size(24.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        text = stringResource(id = AppString.select_size),
        style = MaterialTheme.typography.ht1.copy(
            fontSize = 18.sp,
            color = lightBlack
        )
    )
    Spacer(modifier = Modifier.size(24.dp))
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        list.forEach {
            SelectSizeItem(
                item = it,
                selected = selectedSize == it.name,
                onClick = { size ->
                    onSelectSize(size)
                }
            )
        }
    }
}


@Composable
fun SelectShoeSection() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(3) {
            SelectShoeItem(
                selected = false,
                onClick = {

                }
            )
        }
    }
}


@Composable
fun SelectShoeItem(
    modifier: Modifier = Modifier,
    selected: Boolean = true,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .defaultMinSize(
                minHeight = 72.dp,
                minWidth = 81.dp
            )
            .then(
                if (selected) {
                    Modifier.background(lightBlack, shape = RoundedCornerShape(12.dp))
                } else {
                    Modifier.background(grey, shape = RoundedCornerShape(12.dp))
                }
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick("")
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .padding()
                .width(72.dp)
                .height(40.dp),
            painter = painterResource(id = AppDrawable.ic_yellow_shoe),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SelectSizeItem(
    modifier: Modifier = Modifier,
    item: SizeInfo,
    selected: Boolean = true,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .then(
                when {
                    item.isPresent.not() -> {
                        Modifier
                            .background(white22, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = slateGrey
                            )
                    }

                    selected -> {
                        Modifier
                            .background(white, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = lightBlack
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onClick(item.name)
                            }
                    }

                    else -> {
                        Modifier
                            .background(white, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = slateGrey
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onClick(item.name)
                            }
                    }
                }

            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            text = item.name,
            style = when {
                item.isPresent.not() || selected.not() -> {
                    MaterialTheme.typography.label.copy(
                        color = if (item.isPresent.not()) {
                            slateGrey
                        } else {
                            lightBlack
                        }
                    )
                }

                else -> {
                    MaterialTheme.typography.ht1.copy(color = lightBlack)
                }
            }
        )
    }
}


private fun handleSideEffects(
    effect: ProductDetailScreenSideEffects,
    onAction: (actions: ProductDetailScreenActions) -> Unit,
) {
    when (effect) {
        ProductDetailScreenSideEffects.Back -> {
            onAction(ProductDetailScreenActions.OnBack)
        }

        ProductDetailScreenSideEffects.NoEffect -> {

        }
    }
}