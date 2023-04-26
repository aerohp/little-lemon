package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, database: AppDatabase) {

    val sp = LocalContext.current.getSharedPreferences(Constants.SP_USER_DATA, Context.MODE_PRIVATE)
    val firstName = sp.getString(Constants.SP_KEY_FIRST_NAME, null)
    val isLogin = firstName != null
    NavHost(navController = navController, startDestination = if (isLogin) Home.route else Onboarding.route) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController, database)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}