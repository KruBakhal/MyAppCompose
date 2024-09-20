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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.data.models.TicketDetailModel
import com.example.myappcompose.resolve_parking.theme.button_regular
import com.example.myappcompose.resolve_parking.theme.control_box_outline
import com.example.myappcompose.resolve_parking.theme.gray_color
import com.example.myappcompose.resolve_parking.theme.green_regular
import com.example.myappcompose.resolve_parking.theme.red_regular
import com.example.myappcompose.resolve_parking.theme.text_regular
import com.example.myappcompose.resolve_parking.theme.white
import com.example.myappcompose.ui.theme.fontFamily

@Preview(showBackground = true)
@Composable
fun TicketDetailScreen() {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        ShowTicketDetailScreen(contentPadding.calculateTopPadding(), Modifier)
    }
}

@Composable
fun ShowTicketDetailScreen(contentPadding: Dp, modifier: Modifier) {
    val state = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding + 10.dp)


        ) {
            TopBar()
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .verticalScroll(state)
            ) {
                TimerLayout()
                Spacer(modifier = Modifier.height(10.dp))
                TicketDetailContent(null)
                Spacer(modifier = Modifier.height(10.dp))
                ShowButton()
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Composable
fun ShowButton() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (btnVoid, btnExtend, laySpace1, btnClose, laySpace2, btnManualClose) = createRefs()
        createHorizontalChain(btnVoid, btnExtend, chainStyle = ChainStyle.Spread)

        Button(
            onClick = { },
            modifier = Modifier
                .height(45.dp)
                .padding(0.dp, 0.dp, 5.dp, 0.dp)
                .constrainAs(btnVoid) {
                    start.linkTo(parent.start)
                    end.linkTo(btnExtend.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
            colors = ButtonDefaults.buttonColors(containerColor = red_regular)
        ) {
            Text(text = stringResource(id = R.string.void_title), color = white)
        }
        Button(
            onClick = { },
            modifier = Modifier
                .height(45.dp)
                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                .constrainAs(btnExtend) {
                    start.linkTo(btnVoid.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
            colors = ButtonDefaults.buttonColors(containerColor = button_regular)
        ) {
            Text(
                text = stringResource(id = R.string.extend_ticket), color = white
            )
        }
        Spacer(modifier = Modifier
            .height(10.dp)
            .constrainAs(laySpace1) {
                top.linkTo(btnVoid.bottom)
                bottom.linkTo(btnClose.top)
            })
        Button(
            onClick = { }, modifier = Modifier
                .height(45.dp)
                .constrainAs(btnClose) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(laySpace1.bottom)
                    width = Dimension.fillToConstraints
                }, colors = ButtonDefaults.buttonColors(containerColor = button_regular)
        ) {
            Text(text = stringResource(id = R.string.close_ticket), color = white)
        }
        Spacer(modifier = Modifier
            .height(10.dp)
            .constrainAs(laySpace2) {
                top.linkTo(btnClose.bottom)
                bottom.linkTo(btnManualClose.top)
            })
        Button(
            onClick = { }, modifier = Modifier
                .height(45.dp)
                .border(
                    2.dp, control_box_outline, RoundedCornerShape(30.dp)
                )
                .constrainAs(btnManualClose) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(laySpace2.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(
                text = stringResource(id = R.string.close_ticket_manually), color = red_regular
            )
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = R.drawable.arrow_back),
            modifier = Modifier.size(50.dp),
            contentDescription = "",
            tint = Color.Black
        )
        Text(
            text = stringResource(id = R.string.ticket_detail),
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Start,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            maxLines = 1, color = text_regular
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = green_regular)
        ) {
            Text(
                text =
                stringResource(id = R.string.receipt_btn), color = white
            )
        }
    }
}

@Composable
fun TimerLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val (layBox, tvShowPaymentStatus) = createRefs()
        Box(
            modifier = Modifier
                .width(width = 200.dp)
                .constrainAs(layBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = 10f,
                modifier = Modifier.size(200.dp),
                color = green_regular,
                trackColor = gray_color,
                strokeWidth = 12.dp,
                strokeCap = StrokeCap.Round,
            )
            Text(
                text = "3 hr 55 mins 20 sec",
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 15.dp),
                textAlign = TextAlign.Center,
                color = green_regular,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                minLines = 2,
                fontSize = TextUnit(22f, TextUnitType.Sp)
            )
        }
        Box(
            modifier = Modifier
                .background(
                    green_regular, shape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 0.dp)
                )
                .padding(8.dp, 6.dp)
                .constrainAs(tvShowPaymentStatus) {
                    end.linkTo(parent.end)
                }, contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(id = R.string.paid),
                modifier = Modifier,
                color = white,
                fontSize = TextUnit(12f, TextUnitType.Sp)
            )
        }
    }
}

@Composable
fun TicketDetailContent(ticket: TicketDetailModel?) {
    val ticketNos = ticket?.ticket?.ticketPrints?.firstOrNull()?.ticketSerialNo ?: "--"
    val ticketStatus = ticket?.getTicketStatus(context = LocalContext.current) ?: "--"
    val licensePlate = ticket?.ticket?.licensePlateNo ?: "--"
    val amount = ticket?.ticket?.getTicketAmounts() ?: "--"
    val duration = ticket?.ticket?.getDurationTime() ?: "--"
    val vehicleDetail = ticket?.ticket?.notes ?: "--"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.ticket_nos),
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            color = gray_color,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = ticketNos,
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            color = text_regular
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.ticket_status),
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            color = gray_color,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = ticketStatus,
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            color = text_regular
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                2.dp, control_box_outline, RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        SubTextTicketDetail(
            stringResource(id = R.string.vehicle_license_plate), licensePlate
        )
        SubTextTicketDetail(
            stringResource(id = R.string.ticket_amount), amount
        )
        SubTextTicketDetail(
            stringResource(id = R.string.duration), duration
        )

    }
    Spacer(modifier = Modifier.height(10.dp))
    VehicleDetailContent(vehicleDetail)
    Spacer(modifier = Modifier.height(10.dp))
    TransactionDetailContent(vehicleDetail)


}

@Composable
fun TransactionDetailContent(vehicleDetail: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.tickets_transcation),
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            color = gray_color,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )

        Box(
            modifier = Modifier
                .background(
                    green_regular, shape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 0.dp)
                )
                .padding(8.dp, 6.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(id = R.string.pay_receipt_btn),
                modifier = Modifier, color = white
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                2.dp, control_box_outline, RoundedCornerShape(10.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            repeat(2) {
                ItemTransaction()
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .padding(10.dp, 0.dp), color = gray_color
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun ItemTransaction() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 8.dp)
    ) {
        val (layMode, layTime, layPay) = createRefs()
        Row(modifier = Modifier
            .constrainAs(layMode) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            }
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.transaction_mode),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                fontFamily = fontFamily,
                color = gray_color,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "--",
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                color = text_regular
            )
        }

        Row(
            modifier = Modifier.constrainAs(layTime) {
                start.linkTo(parent.start)
                top.linkTo(layMode.bottom)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.transaction_time),
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                fontFamily = fontFamily,
                color = gray_color,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "--",
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                color = text_regular
            )
        }
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(layPay) {
                    top.linkTo(layMode.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(layTime.bottom)
                    height = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.total_paid),
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                fontFamily = fontFamily,
                color = gray_color,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "--",
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                color = text_regular
            )
        }
    }
}

@Composable
fun VehicleDetailContent(vehicleDetail: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.vehicle_detail),
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            color = gray_color,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Icon(
            painterResource(id = com.google.android.material.R.drawable.material_ic_edit_black_24dp),
            modifier = Modifier.size(25.dp),
            contentDescription = null,
            tint = text_regular
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    BasicTextField(value = vehicleDetail, onValueChange = {
//            devieId = it
    }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .border(
            2.dp, control_box_outline, RoundedCornerShape(10.dp)
        ), textStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Start, fontSize = 18.sp, color = text_regular
    ), keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
    ), singleLine = true, decorationBox = { innerTextField ->
        Row(
            Modifier.padding(10.dp, 10.dp, 15.dp, 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            innerTextField()  //<-- Add this
        }
    })
}

@Composable
fun SubTextTicketDetail(stringResource: String, value: String) {

    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource,
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            color = gray_color,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = value,
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            color = text_regular
        )
    }
}
