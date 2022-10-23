package com.magicworld.mybookpremium.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TitleNote(titleUpdate: String, onTextChanged: (String) -> Unit) {

    TextField(
        value = titleUpdate,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { MyText(text = "Título") },
        maxLines = 1,
        textStyle = TextStyle(fontSize = 20.sp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color(0xFFB2B2B2),
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            )
    )
}

@Composable
fun NoteDescription(descriptionUpdate: String, onTextChanged: (String) -> Unit) {

    TextField(
        value = descriptionUpdate,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxSize(),
        placeholder = { Text("Note") },
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color(0xFFB2B2B2),
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White

        )
    )
}

@Composable
fun MyText(text: String) {
    Text(text = text, fontSize = 20.sp)
}