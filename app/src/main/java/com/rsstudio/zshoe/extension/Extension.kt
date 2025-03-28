package com.rsstudio.worldt2.common.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Navigation Extension Helpers ====================================
 */
fun String.addRouteArgument(argName: String) =
    this.plus("?").plus(argName).plus("={").plus(argName).plus("}")

fun String.withArg(argName: String, argValue: String) =
    "$this?$argName=${argValue}"

fun String.withPath(pathName: String) = this.plus("/$pathName")


fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

sealed class AppTextResource {
    data class DynamicText(val msg: String) : AppTextResource()
    data class StaticText(@StringRes val resourceId: Int, val args: List<String>? = emptyList()) : AppTextResource()
}
@Composable
fun AppTextResource.upiTextToString(context: Context) = when (this) {
    is AppTextResource.DynamicText -> this.msg
    is AppTextResource.StaticText -> context.getString(this.resourceId, *this.args?.toTypedArray() ?: emptyArray())
}