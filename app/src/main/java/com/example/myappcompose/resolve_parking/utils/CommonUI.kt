package com.example.myappcompose.resolve_parking.utils

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myappcompose.R
import com.example.myappcompose.resolve_parking.theme.green_regular
import com.example.myappcompose.resolve_parking.theme.text_regular
import com.example.myappcompose.resolve_parking.theme.white
import com.example.myappcompose.ui.theme.fontFamily

@Composable
public fun TopBar(title: Int, receiptButton: Boolean = false, onClickBack: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)

    ) {
        val (btnBack, layTitle, btnReceipt) = createRefs()
        createHorizontalChain(
            btnBack,
            layTitle,
            btnReceipt,
            chainStyle = ChainStyle.SpreadInside
        )
        Text(
            text = stringResource(id = title),
            modifier = Modifier
                .constrainAs(layTitle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(btnBack.start)
                    end.linkTo(btnReceipt.start)
                    width = Dimension.fillToConstraints
                },
            textAlign = TextAlign.Center,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            maxLines = 1, color = text_regular
        )
        Icon(
            painterResource(id = R.drawable.arrow_back),
            modifier = Modifier
                .size(50.dp)
                .constrainAs(btnBack) {
                    start.linkTo(parent.start)
                    end.linkTo(layTitle.start)
                },
            contentDescription = "",
            tint = Color.Black
        )
        if (receiptButton)
            Button(
                onClick = {
                    onClickBack.invoke()
                },
                modifier = Modifier
                    .height(45.dp)
                    .constrainAs(btnReceipt) {
                        end.linkTo(parent.end)
                    },
                colors = ButtonDefaults.buttonColors(containerColor = green_regular)
            ) {
                Text(
                    text =
                    stringResource(id = R.string.receipt_btn), color = white
                )
            }
    }
}