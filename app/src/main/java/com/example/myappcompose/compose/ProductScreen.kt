package com.example.myappcompose.compose

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.example.CategoryList
import com.example.example.ProductModel
import com.example.myappcompose.R
import com.example.myappcompose.ui.theme.PrimaryColor
import com.example.myappcompose.ui.theme.Purple80
import com.example.myappcompose.ui.theme.TertiaryColor
import com.example.myappcompose.ui.theme.fontFamily
import com.example.myappcompose.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProductScreen(
    category: CategoryList,
    onBackClick: () -> Unit,
    onClick: (ProductModel) -> Unit
) {

    BackHandler {
        onBackClick.invoke()
    }
    Scaffold(modifier = Modifier, topBar = {
        ProductTopAppBar(onBackClick, category.categoryName)
    }) { contentPadding ->
        ProductBelowPart(
            contentPadding.calculateTopPadding(),
            onClick,
            category
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopAppBar(onBackClick: () -> Unit, categoryName: String?) {

    CenterAlignedTopAppBar(title = {
        Text(
            modifier = Modifier.padding(15.dp, 0.dp),
            text = categoryName ?: "Category",
            fontSize = TextUnit(22f, TextUnitType.Sp),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold, maxLines = 1
        )
    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = TertiaryColor,
        titleContentColor = PrimaryColor,

        ), navigationIcon = {
        IconButton(onClick = {
            onBackClick.invoke()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                tint = PrimaryColor

            )
        }
    })
}


@Composable
fun ProductBelowPart(padding: Dp, onClick: (ProductModel) -> Unit, list: CategoryList?) {
    val viewModel: ProductViewModel = viewModel()
    if (list != null) {
        viewModel._categoryProductList.value = list
    }
    val configuration = LocalConfiguration.current
    val isLandscape = (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) ?: false

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
        items(viewModel.categoryProductList.value.productList.distinct()) {
            ItemView(it, onClick)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemView(it: ProductModel, onClick: (ProductModel) -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Purple80),
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clickable { onClick.invoke(it) }
    ) {
        val constraintSet = ConstraintSet {
            val image = createRefFor("image")
            val title = createRefFor("title")
            val guideline = createGuidelineFromBottom(0.2f)
            constrain(image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, margin = 15.dp)
                bottom.linkTo(guideline, margin = 15.dp)
                height = Dimension.fillToConstraints

            }
            constrain(title) {
                top.linkTo(guideline)
                bottom.linkTo(parent.bottom, margin = 3.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
            }
        }
        ConstraintLayout(
            constraintSet, modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .paint(
                    painter = painterResource(id = R.drawable.bg_product),
                    contentScale = ContentScale.Crop
                )
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("image"),
                model = it.mainImage,
                contentDescription = "sadas",
                contentScale = ContentScale.Fit,
                loading = placeholder(R.drawable.baseline_refresh_24),
                failure = placeholder(R.drawable.baseline_refresh_24)
            )
            Text(
                text = "${it.name}",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                maxLines = 1, overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp)
                    .layoutId("title")
            )

        }
    }
}
