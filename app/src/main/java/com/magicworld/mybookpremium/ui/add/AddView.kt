package com.magicworld.mybookpremium.ui.add

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.magicworld.mybookpremium.core.hideKeyboard
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.viewmodel.NotesViewModel

@Composable
fun AddViewNote(navController: NavHostController, notesViewModel: NotesViewModel) {

    Scaffold(
        topBar = {
            TopAppBarAddView(navController , notesViewModel )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BodyAdd(notesViewModel)
        }
    }

}

@Composable
fun TopAppBarAddView(navController: NavHostController, notesViewModel: NotesViewModel) {

    val title by notesViewModel.title.observeAsState(initial = "")
    val description by notesViewModel.description.observeAsState(initial = "")
    val note = Note(id = 0, title, description)
    val context = LocalContext.current
    val activity =  LocalContext.current as Activity

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.White,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                notesViewModel.insertInDatabase(note)
                navController.navigateUp()
                hideKeyboard(context , activity)
            }
            ) {
                Icon(Icons.Outlined.ArrowBackIosNew, null, tint = Color.Black)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Palette, null, tint = Color.DarkGray)
            }

        })
}

@Composable
fun BodyAdd(notesViewModel: NotesViewModel) {

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    notesViewModel.saveNote(title , description)

    Column(Modifier.fillMaxSize()) {
        TitleNote(title) { title = it}
        NoteDescription(description) {description = it}
    }
}

@Composable
fun TitleNote(title: String, onTextChanged: (String) -> Unit) {

    TextField(
        value = title,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { MyText(text = "Título") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color(0xFFB2B2B2),
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            )
    )
}

@Composable
fun NoteDescription(note: String, onTextChanged: (String) -> Unit) {

    TextField(
        value = note,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxSize(),
        placeholder = { Text("Note") },
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color(0xFFB2B2B2),
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White

        )
    )
}


@Composable
fun MyText(text: String) {
    Text(text = text, fontSize = 20.sp)
}


