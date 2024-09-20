package com.example.myappcompose.resolve_parking.compose_ui

import android.graphics.Typeface
import android.widget.TextClock
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.theme.button_regular
import com.example.myappcompose.resolve_parking.theme.hint_color
import com.example.myappcompose.resolve_parking.utils.Constants.DATE_TIME_FORMAT_E_MMM_DD_YYYY
import com.example.myappcompose.resolve_parking.utils.Constants.TIMEZONEIDENTIFIER
import com.example.myappcompose.resolve_parking.utils.getTodayDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


@Preview(showBackground = true)
@Composable
fun StartShiftScreen() {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        StartShiftScreenContent(contentPadding.calculateTopPadding(), Modifier)
    }
}

@Composable
fun StartShiftScreenContent(contentPadding: Dp, modifier: Modifier) {
    val selectedDateTime = getTodayDate()
    Box(modifier = Modifier.fillMaxHeight()) {
        val constraintSet = ConstraintSet {
            val appLogo = createRefFor("app_logo")
            val layBelow = createRefFor("below_lay")
            val layBottom = createRefFor("bottom_lay")
            constrain(appLogo) {
                top.linkTo(parent.top)
                bottom.linkTo(layBelow.top)
            }
            constrain(layBelow) {
                top.linkTo(appLogo.bottom)
                bottom.linkTo(layBottom.top)
            }

            constrain(layBottom) {
//                top.linkTo(appLogo.bottom)
                bottom.linkTo(parent.bottom)
            }

        }
        ConstraintLayout(
            constraintSet, modifier = Modifier
                .fillMaxHeight()
                .padding(contentPadding + 10.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("app_logo"),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.splash_logo),
                    contentDescription = "splash",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .layoutId("below_lay"),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = selectedDateTime,
                    modifier = Modifier.wrapContentWidth(),
                    color = hint_color,
                    fontSize = TextUnit(20f, TextUnitType.Sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 10.dp),
                ) {
                    AndroidView(
                        factory = { context ->
                            val style = com.example.myappcompose.R.style.Theme_MyAppCompat
                            val typeface: Typeface? =
                                ResourcesCompat.getFont(
                                    context,
                                    com.example.myappcompose.R.font.mukta_bold
                                )
                            TextClock(
                                ContextThemeWrapper(context, style),
                                null, style
                            ).apply {
                                format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                                timeZone?.let { this.timeZone = TIMEZONEIDENTIFIER }
                                textSize.let { this.textSize = 25f }
                                setTextColor(
                                    ContextCompat.getColor
                                        (context, R.color.black)
                                )
                                setTypeface(typeface)
                            }
                        },
                        modifier = Modifier.padding(5.dp),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(button_regular),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .layoutId("bottom_lay")
                    .padding(0.dp, 5.dp, 0.dp, 5.dp)
            ) {
                Text(
                    "Start Shift", color = Color.White, fontSize = TextUnit(18f, TextUnitType.Sp)
                )
            }
        }
    }
}

