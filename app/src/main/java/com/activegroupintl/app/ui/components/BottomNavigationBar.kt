package com.activegroupintl.app.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.activegroupintl.app.ui.navigation.Screen
import com.activegroupintl.app.ui.theme.Primary
import com.activegroupintl.app.ui.theme.SurfaceCard
import com.activegroupintl.app.ui.theme.TextSecondary

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = SurfaceCard,
        tonalElevation = 6.dp
    ) {
        Screen.bottomNavItems.forEach { screen ->
            val route = screen.route
            val isSelected = currentRoute == route
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 11.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    selectedTextColor = Primary,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary,
                    indicatorColor = Color(0xFFE8F5E9)
                )
            )
        }
    }
}
