package com.magicworld.mybookpremium.ui.update

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import com.magicworld.mybookpremium.core.MyDropDownMenu
import com.magicworld.mybookpremium.core.hideKeyboard
import com.magicworld.mybookpremium.model.MyColors
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.ui.alertdialog.MyAlertDialog
import com.magicworld.mybookpremium.ui.utils.NoteDescription
import com.magicworld.mybookpremium.ui.utils.TitleNote
import com.magicworld.mybookpremium.viewmodel.UpdateViewModel

@Composable
fun UpdateViewNote(navController: NavHostController, updateViewModel: UpdateViewModel, note: Note) {

    Scaffold(
        topBar = {
            TopAppBarUpdateView(navController, updateViewModel, note)
        },
        floatingActionButtonPosition = FabPosition.End,
        backgroundColor = Color(note.color)
    ) {
        Box {
            BodyUpdate(note, updateViewModel)
        }
    }

}

@Composable
fun TopAppBarUpdateView(
    navController: NavHostController,
    updateViewModel: UpdateViewModel,
    note: Note,
) {
    val updateNote = getUpdateNote(updateViewModel, note)
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showMenu by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                updateViewModel.updateNote(updateNote)
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
            IconButton(onClick = { showDialog = true }) {
                Icon(Icons.Outlined.Delete, null, tint = Color.DarkGray)
            }
        })

    MyAlertDialog(
        title = "Borrar ${note.title}",
        description = "Estas seguro que quieres borrar ${note.title}",
        show = showDialog,
        onDismiss = { showDialog = false }) {
        deleteUser(note, updateViewModel, navController)
    }

    MyDropDownMenu(showMenu,
        onColorSelected = { updateViewModel.saveColor(it) },
        onDismissMenu = { showMenu = false })
}


@Composable
fun getUpdateNote(updateViewModel: UpdateViewModel, note: Note): Note {

    val titleUpdate by updateViewModel.titleUpdate.observeAsState(initial = "")
    val descriptionUpdate by updateViewModel.descriptionUpdate.observeAsState(initial = "")
    val changedColor by updateViewModel.changedColor.observeAsState(initial = MyColors.White.color)
    return Note(note.id, titleUpdate, descriptionUpdate, changedColor)

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

fun deleteUser(note: Note, updateViewModel: UpdateViewModel, navController: NavHostController) {
    updateViewModel.deleteNote(note)
    navController.popBackStack()
}
