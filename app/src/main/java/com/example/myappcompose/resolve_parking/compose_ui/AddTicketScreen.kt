package com.example.myappcompose.resolve_parking.compose_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.data.models.PassTicketModel
import com.example.myappcompose.resolve_parking.theme.button_regular
import com.example.myappcompose.resolve_parking.theme.control_box_outline
import com.example.myappcompose.resolve_parking.theme.fontFamily
import com.example.myappcompose.resolve_parking.theme.gray_color
import com.example.myappcompose.resolve_parking.theme.text_regular
import com.example.myappcompose.resolve_parking.utils.TopBar

@Preview(showBackground = true)
@Composable
fun AddTicketScreen(function: () -> Boolean,submit: (String) -> Unit) {
    Scaffold(
        modifier = Modifier.background(Color.White), containerColor = Color.White
    ) { contentPadding ->
        ShowAddTicketScreen(contentPadding.
        calculateTopPadding(), Modifier,function,submit)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAddTicketScreen(
    contentPadding: Dp,
    modifier: Modifier,
    function: () -> Boolean,
    submit: (String) -> Unit
) {
    val state = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding + 10.dp)


        ) {
            TopBar(R.string.add_ticket, false) {
                //onBackPress
                function.invoke()
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .verticalScroll(state)
            ) {
                val editText = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        2.dp, control_box_outline, RoundedCornerShape(10.dp)
                    )
                var text by remember { mutableStateOf("") }
                var info by remember { mutableStateOf("") }
                var duration by remember { mutableStateOf("") }
                var payOnExist by remember { mutableStateOf(false) }
                var price by remember { mutableStateOf("") }

                AddEditTextField(
                    editText, text, stringResource(id = R.string.please_enter_vehicle_number)
                ) {
                    text = it as String
                }
                Spacer(modifier = Modifier.height(10.dp))

                AddEditTextField(
                    editText, info, stringResource(id = R.string.other_info)
                ) {
                    info = it as String
                }
                Spacer(modifier = Modifier.height(10.dp))

                AddDurationDropDown() { str ->
                    duration = str
                }

                Spacer(modifier = Modifier.height(10.dp))

                AddPayOnExistField(payOnExist) { value ->
                    payOnExist = value
                }
                Spacer(modifier = Modifier.height(10.dp))

                AddPriceField(price) {
                    price = it
                }
                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    userScrollEnabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(6) {
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .background(
                                    color = gray_color, shape = RoundedCornerShape(15.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.splash_logo),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "20.0",
                                color = text_regular,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                fontSize = TextUnit(18f, TextUnitType.Sp),
                            )

                        }
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    onClick = { /*TODO*/

                        val model= PassTicketModel(
                            "AAA", "none", 23.0,
                            0.0, 0.0,
                            0,
                            "1 Hours",
                            false,
                            false,
                            null
                        )
                        submit.invoke(model.toString())
                        },
                    colors = ButtonDefaults.buttonColors(containerColor = button_regular)
                ) {
                    Text(text = stringResource(id = R.string.submit_btn))
                }
            }

        }
    }
}

@Composable
fun AddPriceField(price: String, function: (String) -> Unit) {
    val editTextPrice = Modifier
        .fillMaxWidth()
        .height(50.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .height(50.dp)
            .border(
                2.dp, control_box_outline, RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(10.dp, 0.dp),
            text = "$",
            color = text_regular,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        )

        AddEditTextField(
            editTextPrice, price, stringResource(id = R.string.price_ticket)
        ) {
            function.invoke(it as String)
        }


    }
}

@Composable
fun AddPayOnExistField(payOnExist: Boolean, function: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .height(50.dp)
            .border(
                2.dp, control_box_outline, RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier
                .padding(10.dp, 0.dp)
                .weight(1f),
            text = stringResource(id = R.string.payon_exit),
            color = gray_color,
            fontSize = TextUnit(18f, TextUnitType.Sp)
        )
        Switch(checked = payOnExist, onCheckedChange = {
            function.invoke(it)
        })
        Spacer(modifier = Modifier.width(10.dp))

    }
}

@Composable
fun AddDurationDropDown(onItemClick: (String) -> Unit) {

    TextFieldWithDropdown(
        onItemClick
    )
}

@SuppressLint("PrivateResource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldWithDropdown(
    onItemClick: (String) -> Unit
) {
    val modifier = Modifier

    val interactionSource = remember { MutableInteractionSource() }
    val enabled by remember { mutableStateOf(true) }
    val countriesList = listOf(
        "Germany",
        "Spain",
        "France",
    )

    val textFieldValue = remember { mutableStateOf("") }
    val expandDropDown = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expandDropDown.value,
        onExpandedChange = {
            expandDropDown.value = it
        },
    ) {
        BasicTextField(value = textFieldValue.value,
            onValueChange = { },
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .menuAnchor()
                .border(
                    2.dp, control_box_outline,
                    RoundedCornerShape(10.dp)
                ),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Start,
                fontSize = 18.sp, color = text_regular
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ), readOnly = true,
            singleLine = true,
            decorationBox = { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = textFieldValue.value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.please_enter_duration), color = gray_color
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down),
                            contentDescription = "",
                            Modifier.clickable(onClick = { expandDropDown.value = true }),
                            tint = text_regular
                        )
                    },
                    contentPadding = PaddingValues(10.dp, 5.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                    )
                )
            })
        ExposedDropdownMenu(
            expanded = expandDropDown.value,
            onDismissRequest = { expandDropDown.value = false },
            modifier = Modifier.background(Color.White)
        ) {

            countriesList.forEach { text ->
                DropdownMenuItem(text = {
                    Text(
                        text = text, color = text_regular,
                        fontSize = TextUnit(14f, TextUnitType.Sp)
                    )
                },
                    onClick = {
                        textFieldValue.value = text
                        expandDropDown.value = false
                        onItemClick.invoke(text)
                    })
            }
        }
    }
}

@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    Color.Gray
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Red
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }, text = {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTextField(
    modifier: Modifier = Modifier, text: String, placeHolderText: String, function: (Any) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val enabled by remember { mutableStateOf(true) }
    BasicTextField(value = text, onValueChange = {
        function.invoke(it)
    }, modifier = modifier, textStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Start, fontSize = 18.sp, color = text_regular
    ), keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
    ), singleLine = true, decorationBox = { innerTextField ->

        OutlinedTextFieldDefaults.DecorationBox(
            value = text,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = {
                Text(
                    placeHolderText, color = gray_color
                )
            },
            contentPadding = PaddingValues(10.dp, 5.dp),
        ) {}
//                        innerTextField()  //<-- Add this
    })
}
