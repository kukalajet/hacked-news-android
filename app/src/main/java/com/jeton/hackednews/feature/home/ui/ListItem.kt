package com.jeton.hackednews.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeton.hackednews.core.network.model.Item
import com.jeton.hackednews.core.network.model.Type

@Composable
fun ListItem(item: Item, index: Int) {
    val index = "${index + 1}."
    val title = item.title
    val subtitle = "${item.score} points by ${item.by}"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(Color(0xFFFAF2EC))
    ) {
        Text(
            index,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(title, fontSize = 16.sp)
            Text(subtitle, fontSize = 12.sp, fontWeight = FontWeight.Light)
        }
    }
}

@Preview
@Composable
fun PreviewListItem() {
    val item = Item(
        by = "giuliomagnifica",
        id = 3.3181112E7,
        descendants = 130,
        kids = listOf<Int>(33181617, 33182247, 33181833, 33181638, 33182747, 33182394, 33181418,
            33181502, 33182812, 33182448, 33182989, 33183460, 33181345, 33181681, 33181296,
            33181968, 33181378),
        parts = null,
        score = 169,
        text = null,
        time = 1.665599692E9,
        title = "Students break acceleration world record",
        type = Type.STORY,
        url = "https://www.uni-stuttgart.de/en/university/news/all/Students-break-acceleration-world-record/"
    )

    ListItem(item, 1)
}