package fr.ccm2.projetm2prgd.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.ccm2.projetm2prgd.ui.screen.CountriesScreen
import fr.ccm2.projetm2prgd.ui.screen.LoginScreen
import fr.ccm2.projetm2prgd.ui.screen.MainScreen

object NavigationPath {
    const val MAIN_SCREEN = "main_screen"
    const val LOGIN_SCREEN = "login_screen"
    const val REGISTER_SCREEN = "register_screen"
    const val COUNTRIES_SCREEN = "countries_screen"
}

fun NavGraphBuilder.addMainScreenNav(
    onButtonLoginClick: () -> Unit,
    onButtonCountriesClick: () -> Unit
) {
    composable(
        route = fr.ccm2.projetm2prgd.ui.navigation.NavigationPath.MAIN_SCREEN
    ) {
        MainScreen(
            onButtonLoginClick = { onButtonLoginClick() },
            onButtonCountriesClick = { onButtonCountriesClick() }
        )
    }
}

fun NavGraphBuilder.addLoginScreenNavigation(navController: NavController) {
    composable(
        route = fr.ccm2.projetm2prgd.ui.navigation.NavigationPath.LOGIN_SCREEN,
    ) {
        LoginScreen(navController)
    }
}
fun NavGraphBuilder.addCountriesScreen(navController: NavController) {
    composable(
        route = fr.ccm2.projetm2prgd.ui.navigation.NavigationPath.COUNTRIES_SCREEN,
    ) {
        CountriesScreen(navController)
    }
}

@Composable
fun HomeNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigationPath.MAIN_SCREEN,
    ) {
        addMainScreenNav(
            onButtonLoginClick = {
                navController.navigate(NavigationPath.LOGIN_SCREEN)
            },
            onButtonCountriesClick = {
                navController.navigate(NavigationPath.COUNTRIES_SCREEN)
            }
        )
        addLoginScreenNavigation(navController = navController)
        addCountriesScreen(navController = navController)
    }
}