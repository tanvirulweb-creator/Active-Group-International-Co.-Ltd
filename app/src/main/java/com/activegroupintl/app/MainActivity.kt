package com.activegroupintl.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.activegroupintl.app.ui.components.BottomNavigationBar
import com.activegroupintl.app.ui.components.TopAppBarComponent
import com.activegroupintl.app.ui.components.WhatsAppFab
import com.activegroupintl.app.ui.navigation.Screen
import com.activegroupintl.app.ui.screens.AuthScreen
import com.activegroupintl.app.ui.screens.BookSlotScreen
import com.activegroupintl.app.ui.screens.CoursesScreen
import com.activegroupintl.app.ui.screens.DestinationsScreen
import com.activegroupintl.app.ui.screens.HomeScreen
import com.activegroupintl.app.ui.screens.ProfileScreen
import com.activegroupintl.app.ui.theme.ActiveGroupTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Safety handler: If any exception occurs anywhere on any thread,
        // launch CrashActivity in isolated :crash process so the app never freezes on splash screen.
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            android.util.Log.e("AGI_APP", "Uncaught exception on ${thread.name}", throwable)
            try {
                val stackTrace = android.util.Log.getStackTraceString(throwable)
                val intent = Intent(this@MainActivity, CrashActivity::class.java).apply {
                    putExtra("crash_error", "Crash on ${thread.name}:\n${throwable.javaClass.simpleName}: ${throwable.message}\n\n$stackTrace")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                startActivity(intent)
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(10)
            } catch (e: Throwable) {
                defaultHandler?.uncaughtException(thread, throwable)
            }
        }

        try {
            setContent {
                ActiveGroupTheme {
                    MainApp()
                }
            }
        } catch (e: Throwable) {
            android.util.Log.e("AGI_APP", "Fatal setContent error", e)
            val stackTrace = android.util.Log.getStackTraceString(e)
            val intent = Intent(this, CrashActivity::class.java).apply {
                putExtra("crash_error", "Fatal setContent error:\n${e.javaClass.simpleName}: ${e.message}\n\n$stackTrace")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    fun openWhatsApp() {
        val uri = Uri.parse("https://wa.me/8801335880011")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(intent)
        } catch (e: Exception) { }
    }

    val showTopAndBottomBars = currentRoute != Screen.Auth.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showTopAndBottomBars) {
                TopAppBarComponent(
                    onOfficeClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                        }
                    },
                    onNotificationClick = {
                        navController.navigate(Screen.Home.route)
                    },
                    onProfileClick = {
                        navController.navigate(Screen.Auth.route)
                    }
                )
            }
        },
        bottomBar = {
            if (showTopAndBottomBars) {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (showTopAndBottomBars) {
                WhatsAppFab(onWhatsAppClick = { openWhatsApp() })
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(250)) },
            exitTransition = { fadeOut(animationSpec = tween(250)) }
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToCourses = { navController.navigate(Screen.Courses.route) },
                    onNavigateToBookSlot = { navController.navigate(Screen.BookSlot.route) },
                    onNavigateToDestinations = { navController.navigate(Screen.Destinations.route) },
                    onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                    onDestinationClick = { navController.navigate(Screen.Destinations.route) }
                )
            }

            composable(Screen.Courses.route) {
                CoursesScreen(
                    onBookCourse = {
                        navController.navigate(Screen.BookSlot.route)
                    }
                )
            }

            composable(Screen.BookSlot.route) {
                BookSlotScreen(
                    onBookingSuccess = { }
                )
            }

            composable(Screen.Destinations.route) {
                DestinationsScreen(
                    onSelectDestination = {
                        navController.navigate(Screen.BookSlot.route)
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(Screen.Auth.route) {
                AuthScreen(
                    onAuthSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onContinueAsGuest = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
