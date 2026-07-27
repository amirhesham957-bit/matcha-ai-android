package com.example.matchaai.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matchaai.theme.MatchaGreen

@Composable
fun GlowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    glowColor: Color = MatchaGreen,
    cornerRadius: Dp = 16.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow_pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .drawBehind {
                val frameworkPaint = android.graphics.Paint().apply {
                    color = glowColor.copy(alpha = alpha).toArgb()
                    setShadowLayer(40f, 0f, 0f, this.color)
                }
                drawIntoCanvas { canvas ->
                    canvas.nativeCanvas.drawRoundRect(
                        0f, 0f, size.width, size.height,
                        cornerRadius.toPx(), cornerRadius.toPx(),
                        frameworkPaint
                    )
                }
            }
            .clip(RoundedCornerShape(cornerRadius))
            .background(glowColor)
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF0D0F14), // Dark text on bright button
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = 1.sp
        )
    }
}
