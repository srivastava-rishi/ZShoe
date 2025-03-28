package com.rsstudio.zshoe.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rsstudio.zshoe.ui.theme.ghostGray
import com.rsstudio.zshoe.ui.theme.ht1
import com.rsstudio.zshoe.ui.theme.label
import com.rsstudio.zshoe.ui.theme.lightBlack
import com.rsstudio.zshoe.ui.theme.pebbleMist


@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = true,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .then(
                if (selected) {
                    Modifier.background(lightBlack, shape = RoundedCornerShape(18.dp))
                } else {
                    Modifier
                        .background(ghostGray, shape = RoundedCornerShape(18.dp))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(18.dp),
                            color = pebbleMist
                        )
                }
            )
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onClick(text)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            text = text,
            style = if (selected) {
                MaterialTheme.typography.ht1
            } else {
                MaterialTheme.typography.label
            }
        )
    }
}

// TODO: Add parameter to access different states
@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Chip(
            text = "All",
            selected = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.size(12.dp))
        Chip(
            text = "Air Max",
            selected = false,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.size(12.dp))
        Chip(
            text = "Presto",
            selected = false,
            onClick = {

            }
        )
    }
}