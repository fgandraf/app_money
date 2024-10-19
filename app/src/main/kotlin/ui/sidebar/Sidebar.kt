package ui.sidebar

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.theme.Gray200

@Preview
@Composable
fun Sidebar() {
    val primaryVariant = MaterialTheme.colors.primaryVariant

    Column(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .background(
                Brush.linearGradient(
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(280f, 0f),
                    colors = listOf(Gray200, Color.White, Color.White, Gray200)
                )
            )
            .drawBehind {
                drawLine(
                    color = primaryVariant,
                    start = Offset(279.5.dp.toPx(), 0f),
                    end = Offset(279.5.dp.toPx(), size.height),
                    strokeWidth = 0.5.dp.toPx()
                )
            }
    ) {

        var totalAmount by remember { mutableStateOf("R$ 2.125,00") }
        SidebarHeader(totalAmount)

        Divider()
        SidebarMain(Modifier.weight(1f))
        Divider()

        SidebarFooter()

    }
}