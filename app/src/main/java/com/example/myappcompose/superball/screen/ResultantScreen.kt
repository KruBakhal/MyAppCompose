package com.example.myappcompose.superball.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicesession.superballgame.model.ResultantModel
import com.example.practicesession.superballgame.viewmodel.ResultantViewModel

@Composable
fun ResultantScreenFunc(onBackClick: () -> Unit) {
    val viewModel: ResultantViewModel = hiltViewModel()

    Scaffold(modifier = Modifier, topBar = {
        TopBarAddSave(0, onBackClick = {
            onBackClick.invoke()
        }, onSaveAddClick = {

        })
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            Arrangement.Top,
            Alignment.CenterHorizontally
        ) {
            ResultantBody(
                Modifier, viewModel
            )
        }
    }
}

@Composable
fun ResultantBody(modifier: Modifier, viewModel: ResultantViewModel) {

    viewModel.listOfGame.value?.let {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                .padding(5.dp),
            columns = StaggeredGridCells.Fixed(4),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false
        ) {
            items(it) { item ->
                GameGenericItem(Modifier, item)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun GameGenericItem(modifier: Modifier, it: ResultantModel) {
    it.selected?.let {
        LazyColumn(modifier = modifier.padding(10.dp, 0.dp),
            content = {
                items(it.distinct(), key = { task -> task.id }) { model ->
                    ItemGame(model, Modifier, null, null)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            })
    }
}
