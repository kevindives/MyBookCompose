package com.magicworld.mybookpremium.ui.update

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
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
import com.magicworld.mybookpremium.ui.alertdialog.MyAlertDialog
import com.magicworld.mybookpremium.viewmodel.UpdateViewModel

@Composable
fun UpdateViewNote(navController: NavHostController, updateViewModel: UpdateViewModel, note: Note) {
    Scaffold(
        topBar = {
            TopAppBarUpdateView(navController, updateViewModel, note)
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BodyUpdate(note, updateViewModel)
        }
    }

}

@Composable
fun TopAppBarUpdateView(
    navController: NavHostController,
    updateViewModel: UpdateViewModel,
    note: Note
) {
    var show by rememberSaveable { mutableStateOf(false) }
    val titleUpdate by updateViewModel.titleUpdate.observeAsState(initial = "")
    val descriptionUpdate by updateViewModel.descriptionUpdate.observeAsState(initial = "")
    val updateNote = Note(note.id, titleUpdate, descriptionUpdate)
    val context = LocalContext.current
    val activity =  LocalContext.current as Activity

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.White,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                updateViewModel.updateItem(updateNote)
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
            IconButton(onClick = { show = true }) {
                Icon(Icons.Outlined.Delete, null, tint = Color.DarkGray)
            }
        })

    MyAlertDialog(
        title = "Borrar ${note.title}",
        description = "Estas seguro que quieres borrar ${note.title}",
        show = show,
        onDismiss = { show = false }) {
        deleteUser(note, updateViewModel, navController)
    }
}

@Composable
fun BodyUpdate(note: Note, updateViewModel: UpdateViewModel) {
    var titleUpdate by rememberSaveable { mutableStateOf(note.title) }
    var descriptionUpdate by rememberSaveable { mutableStateOf(note.description) }

    updateViewModel.saveNoteUpdate(titleUpdate, descriptionUpdate)

    Column(Modifier.fillMaxSize()) {
        TitleNote(titleUpdate) { titleUpdate = it }
        NoteDescription(descriptionUpdate) { descriptionUpdate = it }
    }
}

@Composable
fun TitleNote(titleUpdate: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = titleUpdate,
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
fun NoteDescription(descriptionUpdate: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = descriptionUpdate,
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


fun deleteUser(note: Note, updateViewModel: UpdateViewModel, navController: NavHostController) {
    updateViewModel.deleteNote(note)
    navController.popBackStack()
}
