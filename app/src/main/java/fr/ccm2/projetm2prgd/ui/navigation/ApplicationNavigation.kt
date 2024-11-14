package fr.ccm2.projetm2prgd.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import fr.ccm2.projetm2prgd.firebase.viewmodel.FirebaseAuthViewModel
import fr.ccm2.projetm2prgd.ui.screen.CountriesScreen
import fr.ccm2.projetm2prgd.ui.screen.FirebaseAuthScreen
import fr.ccm2.projetm2prgd.ui.screen.MainScreen
import fr.ccm2.projetm2prgd.ui.screen.UserScreen

object NavigationPath {
    const val MAIN_SCREEN = "main_screen"
    const val FIREBASE_AUTH_SCREEN = "firebase_auth_screen"
    const val COUNTRIES_SCREEN = "countries_screen"
}

fun NavGraphBuilder.addMainScreenNav(
    onButtonLoginClick: () -> Unit,
    onButtonCountriesClick: () -> Unit,
    onLogoutClick: () -> Unit,
    firebaseAuthViewModel : FirebaseAuthViewModel
) {
    composable(
        route = fr.ccm2.projetm2prgd.ui.navigation.NavigationPath.MAIN_SCREEN
    ) {
        MainScreen(
            onButtonLoginClick = { onButtonLoginClick() },
            onButtonCountriesClick = { onButtonCountriesClick() },
            onLogoutClick = { onLogoutClick() },
            firebaseAuthViewModel = firebaseAuthViewModel
        )
    }
}

fun NavGraphBuilder.addFirebaseAuthScreenNavigation(navController: NavController) {
    composable(
        route = fr.ccm2.projetm2prgd.ui.navigation.NavigationPath.FIREBASE_AUTH_SCREEN,
    ) {
        FirebaseAuthScreen(navController, FirebaseAuthViewModel())
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
    firebaseAuthViewModel: FirebaseAuthViewModel,
) {
    val currentUser by firebaseAuthViewModel.mCurrentUser.observeAsState()
    NavHost(
        navController = navController,
        startDestination = NavigationPath.MAIN_SCREEN,
    ) {
        addMainScreenNav(
            onButtonLoginClick = {
                navController.navigate(NavigationPath.FIREBASE_AUTH_SCREEN)
            },
            onButtonCountriesClick = {
                navController.navigate(NavigationPath.COUNTRIES_SCREEN)
            },
            onLogoutClick = {
                firebaseAuthViewModel.disconnectUser()
                navController.navigate(NavigationPath.MAIN_SCREEN) {
                    popUpTo(NavigationPath.MAIN_SCREEN) { inclusive = true }
                }
            },
            firebaseAuthViewModel = firebaseAuthViewModel,
            //currentUser = currentUser
        )
        addFirebaseAuthScreenNavigation(navController = navController)
        addCountriesScreen(navController = navController)
        //addUserScreen(navController = navController, viewModel = firebaseAuthViewModel)
    }
}