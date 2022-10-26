package com.magicworld.mybookpremium.ui.add

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import com.magicworld.mybookpremium.core.hideKeyboard
import com.magicworld.mybookpremium.model.MyColors.White
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.ui.utils.MyDropDownMenu
import com.magicworld.mybookpremium.ui.utils.NoteDescription
import com.magicworld.mybookpremium.ui.utils.TitleNote
import com.magicworld.mybookpremium.viewmodel.AddViewModel

@Composable
fun AddViewNote(navController: NavHostController, addViewModel: AddViewModel) {

    var colorSaved by rememberSaveable { mutableStateOf(White.color) }

    Scaffold(
        topBar = {
            TopAppBarAddView(navController, addViewModel) { color -> colorSaved = color }
        },
        floatingActionButtonPosition = FabPosition.End,
        backgroundColor = Color(colorSaved)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BodyAdd(addViewModel)
        }
    }

}

@Composable
fun TopAppBarAddView(
    navController: NavHostController,
    addViewModel: AddViewModel,
    saveColor: (Long) -> Unit,
) {

    var colorSelected by rememberSaveable { mutableStateOf(White.color) }
    val note = getNote(addViewModel, colorSelected)
    var showMenu by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    activity.window.statusBarColor = colorSelected.toInt()

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                addViewModel.insertInDatabase(note)
                navController.navigateUp()
                hideKeyboard(context, activity)
            }
            ) {
                Icon(Icons.Outlined.ArrowBackIosNew, null, tint = Color.Black)
            }
        },
        actions = {
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Outlined.Palette, null, tint = Color.DarkGray)
            }

        })
    MyDropDownMenu(showMenu,
        onColorSelected = { color ->
            colorSelected = color
            saveColor(color)
        },
        onDismissMenu = { showMenu = false })

}

@Composable
fun getNote(addViewModel: AddViewModel, color: Long = White.color): Note {
    val title by addViewModel.title.observeAsState(initial = "")
    val description by addViewModel.description.observeAsState(initial = "")

    return Note(id = 0, title = title, description = description, color)
}

@Composable
fun BodyAdd(addViewModel: AddViewModel) {

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    addViewModel.saveNote(title, description)

    Column(Modifier.fillMaxSize()) {
        TitleNote(title) { title = it }
        NoteDescription(description) { description = it }
    }
}



