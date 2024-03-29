package com.example.myappcompose.practice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.myappcompose.R
import com.example.myappcompose.ui.theme.fontFamily
import kotlinx.coroutines.launch
import kotlin.random.Random

class PracticesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyApp() {

    ConstraintLayoutDemo()
}

@Composable
fun ConstraintLayoutDemo() {
    val constraintSet = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val guideLine = createGuidelineFromTop(0.5f)
        val guideLineB = createGuidelineFromBottom(0.25f)
        constrain(greenBox) {
            top.linkTo(guideLine)
            start.linkTo(parent.start)
        }
        constrain(redBox) {
            top.linkTo(guideLineB)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
        }
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)
    }

    ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(color = Color.Blue)
                .layoutId("greenBox")
        )
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(color = Color.Red)
                .layoutId("redBox")
        )

    }

}

@Composable
fun LazyColumnScrollList() {
    // this lazy column will load rest item on scroll i.e lazy loading on scroll
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(5) { key ->
            ItemCardView(key)
        }
    }

    // load all item in starr
    /*
     val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..20) {
            ItemCardView(i)
        }
    }*/
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleEditTextButtonSnackbar() {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }, content = { paddingValues ->
        // rest of the app's UI
        var textValue by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = textValue,
                label = {
                    Text(text = "Enter Text")
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp),
                onValueChange = {
                    textValue = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(color = Color.Blue)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Button(onClick = {
                coroutineScope.launch {
                    val snackBarResult = snackBarHostState.showSnackbar(
                        message = "Snackbar is here",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    )
                    when (snackBarResult) {
                        SnackbarResult.ActionPerformed -> {
                            Log.d("Snackbar", "Action Performed")
                        }

                        else -> {
                            Log.d("Snackbar", "Snackbar dismissed")
                        }
                    }
                }
                showToast(context, "Click $textValue")
            }) {
                Text(text = "asdsa")
            }
        }
    })

}

@Composable
fun LearnState() {
    val colorState = remember {
        mutableStateOf(Color.Yellow)
    }

    Column() {
        BoxItem(
            Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            colorState.value = it
        }
        Box(
            Modifier
                .weight(1f)
                .fillMaxSize()
                .background(colorState.value)
        )
    }

}

@Composable
fun BoxItem(modifier: Modifier = Modifier, colorState: (Color) -> Unit) {
    Box(modifier = modifier.clickable {
        colorState.invoke(
            Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f,
            )
        )
    })
}

@Composable
fun StylingTextExample() {

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
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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

