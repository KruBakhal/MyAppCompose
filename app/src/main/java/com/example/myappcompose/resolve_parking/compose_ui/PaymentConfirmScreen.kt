package com.example.myappcompose.resolve_parking.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.data.models.PassTicketModel
import com.example.myappcompose.resolve_parking.theme.button_regular
import com.example.myappcompose.resolve_parking.theme.control_box_outline
import com.example.myappcompose.resolve_parking.theme.gray_color
import com.example.myappcompose.resolve_parking.theme.green_regular
import com.example.myappcompose.resolve_parking.theme.hint_color
import com.example.myappcompose.resolve_parking.theme.text_regular
import com.example.myappcompose.resolve_parking.utils.TopBar

@Preview(showBackground = true)
@Composable
fun PaymentConfirmScreen() {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        ShowPaymentConfirmScreen(contentPadding.calculateTopPadding(), Modifier)
    }
}

@Composable
fun ShowPaymentConfirmScreen(contentPadding: Dp, modifier: Modifier) {
    val state = rememberScrollState()
    val model = PassTicketModel(
        "AAA", "none", 23.0, 0.0, 0.0, 0, "1 Hours", false, false, null, false
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding + 10.dp)


        ) {
            TopBar(R.string.print_title, false) {
                //onBackPress
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .verticalScroll(state),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ShowTicketDetail(model)
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.amount_paid),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(20f, TextUnitType.Sp),
                        fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        color = hint_color
                    )
                    Text(
                        text = model.getTotalPaidAmount(),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(24f, TextUnitType.Sp),
                        fontFamily = com.example.myappcompose.ui.theme.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        color = text_regular
                    )
                    Button(
                        modifier = Modifier
                            .size(70.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = gray_color
                        )
                    ) {
                        Text(
                            text = "R",
                            textAlign = TextAlign.Center,
                            fontSize = TextUnit(30f, TextUnitType.Sp),
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = button_regular)
                ) {
                    Text(
                        text = stringResource(id = R.string.receipt_btn),
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                    )
                }
                if (model.isValet) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = button_regular)
                    ) {
                        Text(
                            text = stringResource(id = R.string.key_stub_btn),
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = green_regular)
                ) {
                    Text(
                        text = stringResource(id = R.string.new_ticket_btn),
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                    )
                }
                Box(modifier = Modifier.weight(1f)
                , contentAlignment = Alignment.BottomCenter)
                {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(button_regular, CircleShape)
                        , contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "home"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }
}


@Composable
fun ShowTicketDetail(model: PassTicketModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp, control_box_outline, RoundedCornerShape(10.dp)
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        AddRow(R.string.vehicle_number, model.licPlate)
        Spacer(modifier = Modifier.height(10.dp))
        AddRow(R.string.ticket_amount, model.amount.toString())
        Spacer(modifier = Modifier.height(10.dp))
        AddRow(R.string.tips_amount, model.tipAmount.toString())
        Spacer(modifier = Modifier.height(10.dp))
        AddRow(R.string.transaction_fee, model.transactionAmount.toString())
        Spacer(modifier = Modifier.height(10.dp))
        AddRow(R.string.duration, model.durationStr)
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
fun AddRow(id: Int, licPlate: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = id),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontFamily = com.example.myappcompose.ui.theme.fontFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            color = gray_color
        )
        Text(
            text = licPlate,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontFamily = com.example.myappcompose.ui.theme.fontFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            color = text_regular
        )
    }
}
