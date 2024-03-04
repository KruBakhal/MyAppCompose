package com.example.myappcompose.shopapp.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NameProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("sadas")
    override val count: Int
        get() = values.count()
}