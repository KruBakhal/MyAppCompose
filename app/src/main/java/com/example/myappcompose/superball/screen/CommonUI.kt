package com.example.myappcompose.superball.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myappcompose.R
import com.example.practicesession.superballgame.model.BallTypeModel

@Composable
fun TopBarAddSave(type: Int = 0, onBackClick: () -> Unit, onSaveAddClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "back", tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    onBackClick.invoke()
                }
        )
        if (!type.equals(0)) {
            var resIcon = android.R.drawable.ic_menu_save
            if (type.equals(1)) {
                resIcon = android.R.drawable.ic_menu_add
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .weight(1f, true),
                text = "Result",
                color = Color.White, textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = resIcon),
                contentDescription = "back", tint = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        onSaveAddClick.invoke()
                    }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}

@Composable
fun ItemBall(
    modifier: Modifier = Modifier,
    itemSize: Dp,
    ballTypeModel: BallTypeModel,
    onItemClick: ((BallTypeModel) -> Boolean)? = null
) {

    if (itemSize == 0.dp) {
        item(modifier, ballTypeModel, onItemClick)
    } else
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(10.dp, 5.dp, 5.dp, 5.dp)
        ) {
            item(modifier, ballTypeModel, onItemClick)
        }
}

@Composable
fun item(
    modifier: Modifier,
    ballTypeModel: BallTypeModel,
    onItemClick: ((BallTypeModel) -> Boolean)?
) {
    var result by remember { mutableStateOf(ballTypeModel.selected) }

    val color = if (result) {
        Color.White
    } else
        Color.Black
    val modifer = if (result) {
        modifier
            .size(60.dp)
            .background(Color.Black, CircleShape)
    } else {
        modifier
            .size(60.dp)
            .border(2.dp, Color.Black, CircleShape)
    }
    Box(
        modifier = modifer.clickable {
            if (onItemClick?.invoke(ballTypeModel) == true) {
                result = ballTypeModel.selected
            } else {

            }
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = "${ballTypeModel.number}",
            color = color, textAlign = TextAlign.Center
        )
    }
}
