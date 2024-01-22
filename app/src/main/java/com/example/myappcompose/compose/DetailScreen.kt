package com.example.myappcompose.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.example.ProductModel
import com.example.myappcompose.R
import com.example.myappcompose.ui.theme.TertiaryColor
import com.example.myappcompose.ui.theme.fontFamily
import com.example.myappcompose.viewmodel.DetailViewModel
import com.example.myappcompose.viewmodel.ProductViewModel
import com.google.android.material.textview.MaterialTextView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(product: ProductModel, onBackClick: () -> Unit, onShareClick: (String) -> Unit) {
    val viewModel: DetailViewModel = viewModel()
    viewModel._productModel.value = (product)
    BackHandler {
        onBackClick.invoke()
    }
    Scaffold(modifier = Modifier, topBar = {
        ProductTopAppBar(onBackClick, viewModel.productModel.value?.name)
    }) { contentPadding ->
        DetailBelowPart(
            contentPadding.calculateTopPadding(),
            onShareClick,
            viewModel.productModel.value
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailBelowPart(
    calculateTopPadding: Dp,
    onShareClick: (String) -> Unit,
    product: ProductModel?
) {
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = calculateTopPadding)
            .verticalScroll(state)
    ) {

        val spannedText = product?.description?.let { HtmlCompat.fromHtml(it, 0) }

        val constraintSet = ConstraintSet {
            val image = createRefFor("image")
            val head = createRefFor("head")
            val title = createRefFor("title")
            constrain(image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, margin = 6.dp)
            }
            constrain(head) {
                top.linkTo(image.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(title) {
                top.linkTo(head.bottom, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
            }
        }
        ConstraintLayout(
            constraintSet, modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))

        ) {
            GlideImage(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .layoutId("image")
                    .clickable {
                        product?.url?.let { onShareClick.invoke(it) }
                    },
                model = "${product?.mainImage}",
                contentDescription = "sadas",
                loading = placeholder(R.drawable.baseline_refresh_24),
                failure = placeholder(R.drawable.baseline_refresh_24)
            )

            Text(
                text = "Detail:",
                color = TertiaryColor,
                textAlign = TextAlign.Start,
                fontSize = TextUnit(24f, TextUnitType.Sp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp)
                    .layoutId("head")
            )

           /* Text(
                text = "${product?.description}",
                color = TertiaryColor,
                textAlign = TextAlign.Start,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp, 0.dp)
                    .layoutId("title")
            )*/
            AndroidView(
                modifier   = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp)
                    .layoutId("title"),
                factory = { MaterialTextView(it) },
                update = { it.text = spannedText }
            )
        }
    }
}
