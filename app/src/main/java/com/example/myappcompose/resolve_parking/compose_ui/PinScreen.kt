package com.example.myappcompose.resolve_parking.compose_ui

import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.data.models.ErrorResponse
import com.example.myappcompose.resolve_parking.data.models.SuccessResponse
import com.example.myappcompose.resolve_parking.data.veiwModels.LoginViewModel
import com.example.myappcompose.resolve_parking.theme.button_regular
import com.example.myappcompose.resolve_parking.theme.control_box_outline
import com.example.myappcompose.resolve_parking.theme.green_regular
import com.example.myappcompose.resolve_parking.theme.hint_color

@Preview(showBackground = true)
@Composable
fun PinEnterScreen() {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        val viewModel: LoginViewModel = hiltViewModel()
        ShowPinScreen(
            contentPadding
                .calculateTopPadding(),
            Modifier, viewModel
        ) {

        }
    }
}

@Composable
fun ShowPinScreen(
    contentPadding: Dp,
    modifier: Modifier,
    viewModel: LoginViewModel,
    function: () -> Unit
) {
    val context = LocalContext.current
    var deviceId by remember { mutableStateOf("") }
    val locationId1 by remember { mutableStateOf("") }
    val locationId2 by remember { mutableStateOf("") }
    val locationId3 by remember { mutableStateOf("") }
    val locationId4 by remember { mutableStateOf("") }
    val locationId5 by remember { mutableStateOf("") }
    val locationId6 by remember { mutableStateOf("") }
    val focusRequesters = List(6) { FocusRequester() }
    val locationIds = remember {
        mutableStateListOf(
            locationId1,
            locationId2,
            locationId3,
            locationId4,
            locationId5,
            locationId6
        )
    }
    when (val response = viewModel.verifyPinLiveData.value) {
        is ErrorResponse -> {
            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
        }

        is SuccessResponse -> {
            function.invoke()
        }

        else -> {

        }
    }
    Box(modifier = Modifier.fillMaxHeight()) {
        val constraintSet = ConstraintSet {
            val appLogo = createRefFor("app_logo")
            val layBelow = createRefFor("below_lay")
            constrain(appLogo) {
                top.linkTo(parent.top)
                bottom.linkTo(layBelow.top)
            }
            constrain(layBelow) {
//            top.linkTo(appLogo.bottom)
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
                    text = stringResource(R.string.enter_device_pin_id),
                    modifier = Modifier.fillMaxWidth(),
                    color = hint_color,
                    fontSize = TextUnit(20f, TextUnitType.Sp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                BasicTextField(
                    value = deviceId,
                    onValueChange = { deviceId = it },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            2.dp, control_box_outline, RoundedCornerShape(10.dp)
                        ),
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp, color = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier.padding(15.dp, 10.dp, 15.dp, 10.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()  //<-- Add this
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.enter_location_id),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    color = hint_color,
                    fontSize = TextUnit(20f, TextUnitType.Sp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement =
                    Arrangement.Absolute.spacedBy(
                        5.dp, Alignment.CenterHorizontally
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    for (i in 0 until 6) {
                        BasicTextField(
                            value = locationIds[i],
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) {
                                    locationIds[i] = newValue
                                    if (newValue.isNotEmpty() && i < 5) {
                                        focusRequesters[i + 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(0.dp)
                                .border(2.dp, control_box_outline, RoundedCornerShape(10.dp))
                                .focusRequester(focusRequesters[i])
                                .onKeyEvent { keyEvent ->
                                    if (keyEvent.nativeKeyEvent.action == KeyEvent.ACTION_UP && keyEvent.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL) {
                                        if (locationIds[i].isEmpty() && i > 0) {
                                            focusRequesters[i - 1].requestFocus()
                                            return@onKeyEvent true
                                        }
                                    }
                                    false
                                },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = if (i < 5) ImeAction.Next else ImeAction.Done
                            ),
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .fillMaxSize()
                                        .padding(0.dp, 0.dp, 0.dp, 0.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    innerTextField()  //<-- Add this
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        var locationId =""
                        locationIds.forEach {
                            locationId+=""+it
                        }
                        if (locationId.isNotEmpty()) {

                            viewModel.verifyPin(deviceId, "ccc", locationId)
                        } else {
                            // Handle empty fields here (e.g., show an error message)
                            Toast.makeText(
                                context,
                                context.getString(R.string.enter_device_serial_id),
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    },
                    colors = ButtonDefaults.buttonColors(button_regular),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(0.dp, 5.dp, 0.dp, 5.dp)
                ) {
                    Text(
                        "Verify", color = Color.White, fontSize = TextUnit(18f, TextUnitType.Sp)
                    )
                }
            }
        }
        if (viewModel.showProgressDialog.value)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier) {
                    Image(
                        painterResource(id = R.drawable.splash_logo),
                        contentDescription = "splash",
                        modifier = Modifier
                            .size(100.dp)
                            .shadow(0.dp, CircleShape)
                            .padding(10.dp)
                            .align(Alignment.Center)
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.size(120.dp),
                        color = green_regular,
                        trackColor = Color.Transparent,
                        strokeWidth = 10.dp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.please_wait),
                    modifier = Modifier,
                    color = button_regular,
                    fontSize = TextUnit(20f, TextUnitType.Sp)
                )
            }
    }
}