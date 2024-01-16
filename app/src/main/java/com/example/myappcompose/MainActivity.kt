package com.example.myappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myappcompose.ui.theme.MyAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppComposeTheme {
                MyApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        StylingTextExample()
    }
}

@Composable
fun StylingTextExample() {
    val fontFamily = FontFamily(
        Font(R.font.mukta_light, FontWeight.Light),
        Font(R.font.mukta_medium, FontWeight.Medium),
        Font(R.font.mukta_regular, FontWeight.Normal),
        Font(R.font.mukta_semibold, FontWeight.SemiBold),
        Font(R.font.mukta_extralight, FontWeight.ExtraLight),
        Font(R.font.mukta_bold, FontWeight.Bold),
        Font(R.font.mukta_extrabold, FontWeight.ExtraBold),
    )
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Blue, fontSize = 26.sp
                )
            ) {
                append("J")
            }
            append("etpack")
            withStyle(
                style = SpanStyle(
                    color = Color.Blue, fontSize = 26.sp
                )
            ) {
                append(" C")
            }
            append("ompose")

        },
        fontSize = 20.sp,
        color = Color.Black,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}


@Composable
fun ItemCardView(i: Int) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(10.dp), shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            Image(
                painterResource(id = R.drawable.sample),
                contentDescription = "Image $i",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black), startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Image sdjhsjdsh $i",
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }

    }

}

