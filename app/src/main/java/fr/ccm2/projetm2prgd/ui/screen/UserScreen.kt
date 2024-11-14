package fr.ccm2.projetm2prgd.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.ccm2.projetm2prgd.firebase.viewmodel.FirebaseAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navController: NavController,
    viewModel: FirebaseAuthViewModel
) {
    val currentUser = viewModel.mCurrentUser.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User Profile") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenue, ${currentUser?.email ?: "Invité"}",
                fontSize = 24.sp
            )
            Button(
                onClick = {
                    viewModel.disconnectUser()
                    navController.navigate("login") {
                        popUpTo("user") { inclusive = true }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Déconnexion")
            }
        }
    }
}