package fr.ccm2.projetm2prgd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import fr.ccm2.projetm2prgd.firebase.viewmodel.FirebaseAuthViewModel
import fr.ccm2.projetm2prgd.ui.navigation.HomeNavHost
import fr.ccm2.projetm2prgd.ui.theme.ProjetM2PRGDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjetM2PRGDTheme {
                val navController = rememberNavController()
                val firebaseAuthViewModel: FirebaseAuthViewModel = viewModel()
                val currentUser by firebaseAuthViewModel.mCurrentUser.observeAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        HomeNavHost(
                            navController = navController,
                            firebaseAuthViewModel = firebaseAuthViewModel,
                        )
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    /*Text(
        text = "Hello $name!",
        modifier = modifier
    )*/
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjetM2PRGDTheme {
        Greeting("Android")
    }
}