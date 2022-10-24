@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.magicworld.mybookpremium.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.magicworld.mybookpremium.R
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.model.Routes.AddView
import com.magicworld.mybookpremium.model.Routes.UpdateView
import com.magicworld.mybookpremium.viewmodel.ListViewModel
import kotlinx.coroutines.launch

@Composable
fun ListViewNotes(navController: NavHostController, listViewModel: ListViewModel) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBarListView(listViewModel) { coroutineScope.launch { scaffoldState.drawerState.open() } }
        },
        floatingActionButton = {
            FloatingListBottom(navController)
        },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState,
        drawerContent = { MyDrawer(listViewModel) { coroutineScope.launch { scaffoldState.drawerState.close() } } }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)) {
            BodyList(listViewModel, navController)
        }
    }
}


@Composable
fun TopAppBarListView(listViewModel: ListViewModel, onClickDrawer: () -> Unit) {

    var typeView by rememberSaveable { mutableStateOf(true) }

    TopAppBar(
        title = { ListTitle() },
        backgroundColor = Color(0xFFE6E6FA),
        modifier = Modifier
            .padding(13.dp)
            .clip(RoundedCornerShape(100)),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }
            ) {
                Icon(Icons.Outlined.Menu, null, tint = Color.DarkGray)
            }
        },
        actions = {
            IconButton(onClick = {
                typeView = !typeView
                listViewModel.saveTypeView(typeView)
            }
            ) {
                if (typeView) {
                    Icon(Icons.Filled.GridView, null, tint = Color.DarkGray)
                } else {
                    Icon(Icons.Outlined.ViewAgenda, null, tint = Color.DarkGray)
                }

            }
            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.oscurologo),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                )
            }
        }
    )
}

@Composable
fun ListTitle() {
    Text(text = "My Book ", color = Color.DarkGray, fontSize = 15.sp)
}

@Composable
fun FloatingListBottom(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate(AddView.route) },
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Crear nota", tint = Color.White,
            modifier = Modifier.scale(2f, 2f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyList(listViewModel: ListViewModel, navController: NavHostController) {
    val typeView by listViewModel.typeView.observeAsState(initial = true)
    val listNote by listViewModel.readAllData.observeAsState(listOf())
    if (typeView) {
        LazyColumn{
            items(listNote) { note ->
                ItemNote(note = note, navController)
            }
        }
    } else {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            content = {
                items(listNote) { note ->
                    ItemNote(note = note, navController)
                }
            },
            contentPadding = PaddingValues(8.dp)
        )
    }

}

@Composable
fun ItemNote(note: Note, navController: NavHostController) {
    MyCard(note) {
        navController.currentBackStackEntry?.savedStateHandle?.set("note", note)
        navController.navigate(UpdateView.route)
    }
}




