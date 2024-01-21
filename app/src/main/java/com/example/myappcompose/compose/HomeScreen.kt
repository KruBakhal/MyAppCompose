package com.example.myappcompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myappcompose.R
import com.example.myappcompose.viewmodel.BrandViewModel


@Composable
fun HomeScreen(
    onClick: () -> Unit
) {
    val viewModel: BrandViewModel = hiltViewModel()
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier,
        topBar = {
            HomeTopAppBar()
        }
    ) { contentPadding ->
        HomeBelowPart(contentPadding.calculateTopPadding())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        },
        modifier = modifier.background(Color.Black)
    )
}


@Composable
fun HomeBelowPart(padding: Dp) {

    val list = mutableListOf<String>("asda", "sds", "asda", "sds")

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)

    ) {
        items(list.size) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "$it", color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}