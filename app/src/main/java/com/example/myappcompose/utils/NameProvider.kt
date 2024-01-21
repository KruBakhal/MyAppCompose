package com.example.myappcompose.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NameProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("sadas")
    override val count: Int
        get() = values.count()
}