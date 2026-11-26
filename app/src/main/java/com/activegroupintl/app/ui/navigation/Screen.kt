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
    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object Courses : Screen("courses", "Courses", Icons.Default.School)
    data object BookSlot : Screen("book_slot", "Book Slot", Icons.Default.CalendarMonth)
    data object Destinations : Screen("destinations", "Destinations", Icons.Default.Public)
    data object Profile : Screen("profile", "Profile", Icons.Default.AccountCircle)
    data object Auth : Screen("auth", "Sign In", Icons.Default.Lock)

    companion object {
        // Use custom getter to prevent companion object class-loading circular null reference
        val bottomNavItems: List<Screen>
            get() = listOf(Home, Courses, BookSlot, Destinations, Profile)
    }
}
