package com.example.myappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myappcompose.compose.ShopApp
import com.example.myappcompose.ui.theme.MyAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ShopApp()
            MyAppBasic()

        }
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
     fun MyAppBasic() {
        val windowSizeClass = calculateWindowSizeClass(this)
        MySootheApp(windowSizeClass)
    }

    @Composable
    fun MySootheApp(windowSize: WindowSizeClass) {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                showPortrait()
            }

            WindowWidthSizeClass.Expanded -> {
                showLandscape()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview(showBackground = true)
    fun showPortrait() {
        MyAppComposeTheme {
            Scaffold(bottomBar = { BottomBarNavigationPortrait() }) { paddingValues ->
                HomeScreen(Modifier.padding(paddingValues))
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview(showBackground = true)
    fun showLandscape() {
        MyAppComposeTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                Row {
                    BottomBarNavigationLandscape()
                    HomeScreen(Modifier.padding())
                }
            }
        }
    }

    /* @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
  */   @Composable
    fun ScreenContentPreview() {
        MyAppComposeTheme {
            HomeScreen(Modifier.padding(16.dp))

        }
    }

    @Composable
    private fun BottomBarNavigationPortrait(modifier: Modifier = Modifier) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier
        ) {
            NavigationBarItem(icon = {
                Icon(
                    imageVector = Icons.Default.Home, contentDescription = ""
                )
            }, label = {
                Text(text = stringResource(id = R.string.home))
            }, selected = true, onClick = {})
            NavigationBarItem(icon = {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = ""
                )
            }, label = {
                Text(text = stringResource(id = R.string.profile))
            }, selected = false, onClick = {})

        }
    }

    @Composable
    private fun BottomBarNavigationLandscape(modifier: Modifier = Modifier) {
        NavigationRail(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier.padding(end = 8.dp)
        ) {
            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationRailItem(icon = {
                    Icon(
                        imageVector = Icons.Default.Home, contentDescription = ""
                    )
                }, label = {
                    Text(text = stringResource(id = R.string.home))
                }, selected = true, onClick = {})
                NavigationRailItem(icon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = ""
                    )
                }, label = {
                    Text(text = stringResource(id = R.string.profile))
                }, selected = false, onClick = {})

            }
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier) {

        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            SearchBox(Modifier.padding(horizontal = 16.dp))
            MiddleBox(R.string.align_body) {
                AlignYourBodyRow(Modifier)
            }
            MiddleBox(R.string.fav_collection) {
                FavouriteCollectionColumns(Modifier)
            }
            Spacer(Modifier.height(16.dp))
        }
    }

    private @Composable
    fun MiddleBox(@StringRes text: Int, content: @Composable () -> Unit) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = stringResource(id = text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
            )
            content()
        }

    }

    @Composable
    fun AlignYourBodyRow(modifier: Modifier) {
        val list = mutableListOf<String>("sadas", "sadas", "sadas", "sadas")

        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) {
                AlignBodyItemView()
            }
        }
    }

    @Composable
    fun FavouriteCollectionColumns(modifier: Modifier) {
        val list = mutableListOf<String>("sadas", "sadas", "sadas", "sadas")

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = modifier.height(168.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(list) {
                FavCollectionItemView()
            }
        }
    }

    private @Composable
    fun AlignBodyItemView() {

        Column(
            modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample),
                contentDescription = "sample demo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Inversion",
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            )


        }
    }

    private @Composable
    fun FavCollectionItemView(modifier: Modifier = Modifier) {
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
        ) {
            Row(
                modifier = Modifier.width(255.dp), verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = painterResource(id = R.drawable.sample),
                    contentDescription = "sample demo",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "Inversion",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private
    @Composable
    fun SearchBox(modifier: Modifier) {

        TextField(
            value = "",
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedLabelColor = MaterialTheme.colorScheme.surface,
                focusedLabelColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}
