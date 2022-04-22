package com.example.mangement.model

sealed class SelectedBottomNavigation{
    object Status:SelectedBottomNavigation()
    object Home:SelectedBottomNavigation()
    object Chat:SelectedBottomNavigation()

}
