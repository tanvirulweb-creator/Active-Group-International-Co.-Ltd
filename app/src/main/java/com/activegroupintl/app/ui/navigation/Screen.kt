package com.activegroupintl.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Courses : Screen("courses", "Courses", Icons.Default.School)
    object BookSlot : Screen("book_slot", "Book Slot", Icons.Default.CalendarMonth)
    object Destinations : Screen("destinations", "Destinations", Icons.Default.Public)
    object Profile : Screen("profile", "Profile", Icons.Default.AccountCircle)
    object Auth : Screen("auth", "Sign In", Icons.Default.Lock)

    companion object {
        val bottomNavItems = listOf(Home, Courses, BookSlot, Destinations, Profile)
    }
}
