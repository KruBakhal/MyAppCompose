package com.example.myappcompose.resolve_parking.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.data.models.PassTicketModel
import com.example.myappcompose.resolve_parking.theme.button_regular
import com.example.myappcompose.resolve_parking.theme.gray_color
import com.example.myappcompose.resolve_parking.theme.text_regular
import com.example.myappcompose.resolve_parking.utils.TopBar

@Preview(showBackground = true)
@Composable
fun ConfirmSalesScreen() {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        ShowConfirmSalesScreen(contentPadding.calculateTopPadding(), Modifier)
    }
}

@Composable
fun ShowConfirmSalesScreen(contentPadding: Dp, modifier: Modifier) {
    val state = rememberScrollState()
    val model = PassTicketModel(
        "AAA", "none", 23.0,
        0.0, 0.0,
        0,
        "1 Hours",
        false,
        false,
        null
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding + 10.dp)


        ) {
            TopBar(R.string.confirm_sale_title, false) {
                //onBackPress
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .verticalScroll(state),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.vehicle_number),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    color = gray_color
                )
                Text(
                    text = model.licPlate,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    color = text_regular
                )
                Text(
                    text = stringResource(id = R.string.duration),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    color = gray_color
                )
                Text(
                    text = model.durationStr,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    color = text_regular
                )
                Text(
                    text = stringResource(id = R.string.shift_type),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    color = gray_color
                )
                Spacer(modifier = Modifier.height(10.dp))

                PrimaryTabRowDemo()
                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(
                        id = if (model.payOnExist) R.string.checkout_type_str
                        else R.string.payment_type_str
                    ),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                    fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    color = gray_color
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (!model.payOnExist) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = button_regular)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cash_btn),
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = button_regular)
                    ) {
                        Text(
                            text = stringResource(id = R.string.card_btn),
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                        )
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = button_regular)
                    ) {
                        Text(
                            text = stringResource(id = R.string.checkout),
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun PrimaryTabRowDemo() {
    val isValetActive = remember {
        true
    }
    var isValetSelected = remember {
        true
    }
    if (!isValetActive) {
        isValetSelected = false
    }
    isValetSelected = false

    ConstraintLayout(modifier = Modifier.wrapContentSize()) {

        val (btnValet, btnSelf) = createRefs()

        if (isValetActive) Button(modifier = Modifier
            .wrapContentSize()
            .constrainAs(btnValet) {
                start.linkTo(parent.start)
            }, onClick = {}, colors = ButtonDefaults.buttonColors(
            containerColor = if (!isValetSelected) {
                gray_color
            } else button_regular
        )
        ) {
            Text(
                modifier = Modifier.padding(10.dp, 5.dp),
                text = stringResource(id = R.string.valet_btn),
                fontSize = TextUnit(18f, TextUnitType.Sp),
            )
        }

        Button(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(btnSelf) {
                    end.linkTo(parent.end)
                }
                .padding(85.dp, 0.dp, 0.dp, 0.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = if (!isValetActive) {
                    button_regular
                } else {
                    if (isValetSelected) {
                        gray_color
                    } else button_regular
                }
            ),
        ) {
            Text(
                modifier = Modifier.padding(10.dp, 5.dp),
                text = stringResource(id = R.string.self_btn),
                fontSize = TextUnit(18f, TextUnitType.Sp),

                )
        }

    }

}

