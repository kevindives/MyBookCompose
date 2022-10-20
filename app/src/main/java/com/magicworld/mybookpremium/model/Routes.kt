package com.magicworld.mybookpremium.model

sealed class Routes (val route:String){
    object ListView: Routes("listview")
    object AddView: Routes("addview")
    object UpdateView: Routes("updateview")
}