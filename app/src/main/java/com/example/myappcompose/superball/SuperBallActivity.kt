package com.example.myappcompose.superball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myappcompose.superball.screen.SelectionScreenFunc
import com.example.myappcompose.superball.screen.SuperBallApp
import com.example.myappcompose.ui.theme.MyAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperBallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppComposeTheme {
                SuperBallApp()
            }
        }
    }

}