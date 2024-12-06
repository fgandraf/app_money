package view.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Light
import com.adamglin.phosphoricons.light.X

@Composable
fun DialogTitleBar(
    title: String,
    backgroundColor: Color = Color.Transparent,
    onCloseRequest: () -> Unit
) {

    Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)).background(backgroundColor)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(5.dp).padding(start = 5.dp)
        ) {
            TextPrimary(
                text = title,
                size = 12.sp,
                color = MaterialTheme.colors.secondary,
            )
            Box(
                Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable(onClick = onCloseRequest)
            ) {
                Icon(
                    imageVector = PhosphorIcons.Light.X,
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(18.dp).align(Alignment.Center)
                )
            }
        }

    }
}