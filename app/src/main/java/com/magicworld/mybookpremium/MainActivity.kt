package com.magicworld.mybookpremium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.model.Routes.*
import com.magicworld.mybookpremium.ui.add.AddViewNote
import com.magicworld.mybookpremium.ui.list.ListViewNotes
import com.magicworld.mybookpremium.ui.theme.MyBookPremiumTheme
import com.magicworld.mybookpremium.ui.update.UpdateViewNote
import com.magicworld.mybookpremium.viewmodel.AddViewModel
import com.magicworld.mybookpremium.viewmodel.ListViewModel
import com.magicworld.mybookpremium.viewmodel.UpdateViewModel

class MainActivity : ComponentActivity() {

    private val addViewModel: AddViewModel by viewModels()
    private val updateViewModel: UpdateViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookPremiumTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = ListView.route) {
                        composable(ListView.route) { ListViewNotes(navController, listViewModel) }
                        composable(AddView.route) { AddViewNote(navController, addViewModel) }
                        composable(UpdateView.route) {
                            val note = navController.previousBackStackEntry?.savedStateHandle?.get<Note>("note")
                            note?.let {
                                UpdateViewNote(navController, updateViewModel , note)
                            }
                        }
                    }
                }
            }
        }
    }
}

