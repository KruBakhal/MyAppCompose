package com.example.myappcompose.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import dagger.hilt.android.AndroidEntryPoint
import com.example.myappcompose.practice.States.GetStartWithStates
import com.example.myappcompose.shopapp.compose.ShopApp
import com.example.myappcompose.ui.theme.MyAppComposeTheme
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ShopApp()
            MyAppComposeTheme {
                MainScreen()
            }


        }
    }

    @Preview
    @Composable
    private fun MainScreen() {
        val showMainScreen = rememberSaveable { mutableStateOf(false) }
        if (showMainScreen.value) {
            val param = rememberSaveable { intent.getBooleanExtra("compose", true) }
            if (param) {
                ShopApp()
            } else {
                GetStartWithStates()
            }
        } else {
            ShowSplashScreen {
                showMainScreen.value = true
            }
        }

    }

    private @Composable
    fun ShowSplashScreen(onFinsihLoading: () -> Unit) {
        val rememberState by rememberUpdatedState( onFinsihLoading)
        LaunchedEffect(key1 = Unit, block = {
            delay(5000)
            rememberState()
        })
        Scaffold(modifier = Modifier.fillMaxSize(), content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Splash Screen", modifier = Modifier,
                    fontSize = TextUnit(20f, TextUnitType.Sp), color = Color.White
                )

            }
        }, containerColor = Color.Blue)

    }


}
