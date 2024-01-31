package com.example.myappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.example.myappcompose.Practice.States.GetStartWithStates
import com.example.myappcompose.ui.theme.MyAppComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ShopApp()
            MyAppComposeTheme() {

                GetStartWithStates()
            }


        }
    }



}
