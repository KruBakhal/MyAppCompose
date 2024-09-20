package com.example.myappcompose.superball.screen

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myappcompose.R
import com.example.practicesession.superballgame.model.BallTypeModel
import com.example.practicesession.superballgame.model.SuperBallModel


@Composable
fun SuperBallHome(function: (SuperBallModel) -> Unit, onResultant: () -> Unit) {
    Scaffold(modifier = Modifier, topBar = {

    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(
                    contentPadding
                        .calculateTopPadding()
                )
                .fillMaxSize(), Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            var selected by remember { mutableStateOf(0) }
            var gName by remember { mutableStateOf("Select Game") }
            val context = LocalContext.current
            HomePart(onItemClick = { it, name ->
                selected = it
                gName = name
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                val position = selected
                if (position == 0) {
                    Toast.makeText(context, "Select Game First", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val bundle = Bundle()
                var superMOdel: SuperBallModel? = null
                var gameName = ""
                var maxNormal = 5;
                var maxSuper = 1;
                var nRangeMin: Int = 0
                var nRangeMax: Int = 0
                var sRangeMin: Int = 0
                var sRangeMax: Int = 0
                gameName = gName
                when (position) {


                    1 -> {
                        maxNormal = 5;
                        maxSuper = 1;
                        nRangeMin = 1
                        nRangeMax = 40
                        sRangeMin = 1
                        sRangeMax = 12
                    }

                    2 -> {
                        maxNormal = 5;
                        maxSuper = 1;
                        nRangeMin = 10
                        nRangeMax = 40
                        sRangeMin = 1
                        sRangeMax = 12
                    }

                    3 -> {
                        maxNormal = 5;
                        maxSuper = 1;
                        nRangeMin = 15
                        nRangeMax = 30
                        sRangeMin = 1
                        sRangeMax = 15
                    }

                    4 -> {
                        maxNormal = 5;
                        maxSuper = 1;
                        nRangeMin = 1
                        nRangeMax = 40
                        sRangeMin = 0
                        sRangeMax = 0
                    }

                    5 -> {

                        maxNormal = 5;
                        maxSuper = 1;
                        nRangeMin = 55
                        nRangeMax = 85
                        sRangeMin = 0
                        sRangeMax = 0

                    }
                }
                superMOdel = SuperBallModel(
                    gameName = gameName,
                    selectedList = arrayListOf<BallTypeModel>(),
                    maxNormal = maxNormal,
                    maxSuper = maxSuper,
                    nRangeMin = nRangeMin,
                    nRangeMax = nRangeMax,
                    sRangeMin = sRangeMin,
                    sRangeMax = sRangeMax
                )


                function.invoke(superMOdel)
            }) {
                Text(text = "Next")
            }
            Button(onClick = {
                onResultant.invoke()
            }) {
                Text(text = "Result")
            }
        }
    }
}

@Composable
fun HomePart(onItemClick: ((Int, String) -> Unit)? = null) {

    val itemList = stringArrayResource(id = R.array.game_array).toList()
    var text by remember { mutableStateOf("Select Game") }

    var expanded by remember { mutableStateOf(false) }

    Spinner(text = text, expanded = expanded, list = itemList, onItemClick = { index, it ->
        text = it
        onItemClick?.invoke(index, it)
        expanded = false
    }, onClick = {
        expanded = !expanded
    })
}

@Composable
fun Spinner(
    text: String = "Select something!",
    expanded: Boolean = false,
    list: List<String>,
    onItemClick: ((Int, String) -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(text = text)
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = "ArrowDropDown",
        )
        DropdownMenu(
            expanded = expanded, onDismissRequest = onClick
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    onItemClick?.invoke(index, item)
                }, text = {
                    Text(text = item)
                })
            }
        }
    }
}