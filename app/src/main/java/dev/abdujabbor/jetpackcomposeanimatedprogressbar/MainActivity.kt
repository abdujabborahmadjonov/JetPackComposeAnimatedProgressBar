package dev.abdujabbor.jetpackcomposeanimatedprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressBar(percentage = 0.9F, number = 100)
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 60.dp,
    strokeWidth: Dp = 8.dp,
    animateDuration: Int = 2000,
    animationDelay: Int = 2
) {
    var animatedPlayed by remember {
        mutableStateOf(false)
    }
    val curPersentage = animateFloatAsState(
        targetValue = if (animatedPlayed) percentage else 0f, animationSpec = tween(
            durationMillis = animateDuration, delayMillis = animationDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animatedPlayed = true
    }
    Box(
        modifier = Modifier.size(radius * 2f), contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = Color.Green,
                -90f,
                360 * curPersentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (curPersentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize
        )
    }
}
