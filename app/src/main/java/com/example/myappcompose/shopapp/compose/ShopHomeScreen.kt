package com.example.myappcompose.shopapp.compose

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.example.CategoryList
import com.example.myappcompose.R
import com.example.myappcompose.ui.theme.PrimaryColor
import com.example.myappcompose.ui.theme.TertiaryColor
import com.example.myappcompose.ui.theme.fontFamily
import com.example.myappcompose.shopapp.viewmodel.CategoryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ShopHomeScreen(onClick: (CategoryList) -> Unit) {

    Scaffold(
        modifier = Modifier,
        topBar = {
            HomeTopAppBar()
        }
    ) { contentPadding ->
        HomeBelowPart(contentPadding.calculateTopPadding(), onClick)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = TextUnit(26f, TextUnitType.Sp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TertiaryColor,
            titleContentColor = PrimaryColor
        )
    )
}


@Composable
fun HomeBelowPart(padding: Dp, onClick: (CategoryList) -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) ?: false
    val viewModel: CategoryViewModel = hiltViewModel()
    val list = viewModel.categoryList.collectAsState()
    if (list.value.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading...", color = TertiaryColor,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 20.dp)
            )
        }
    } else {
        var spanCount = 2
        if (isLandscape) {
            spanCount = 3
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(spanCount),
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding)

        ) {
            items(list.value.distinctBy { it.categoryName }) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(130.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .paint(
                            painter
                            = painterResource(id = R.drawable.bg_category),
                            contentScale = ContentScale.Crop
                        )
                        .clickable {
                            onClick.invoke(it)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${capitalizeFirstLetter(it.categoryName?.replace("-", " "))}",
                        color = Color.White, textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 20.dp)
                    )
                }
            }
        }
    }


}

private fun capitalizeFirstLetter(text: String?): String {
    return text?.substring(0, 1)?.uppercase() + text?.substring(1)?.lowercase()
}
