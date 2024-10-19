package fr.ccm2.projetm2prgd.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import fr.ccm2.projetm2prgd.R
import fr.ccm2.projetm2prgd.architecture.RetrofitBuilder
import fr.ccm2.projetm2prgd.data.model.CountryObjectDto
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
){
    // MutableState pour stocker la liste des pays récupérés
    var countriesList by remember { mutableStateOf<List<CountryObjectDto>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    // Appel de l'API au chargement de la page
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Appel de l'API pour récupérer les pays
                countriesList = RetrofitBuilder.getCountries().getCountries()
                isLoading = false // Indique que le chargement est terminé
            } catch (e: Exception) {
                errorMessage = "Erreur lors de la récupération des pays"
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = LocalContext.current.getString(R.string.Countries))  },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column {
            // Affichage du contenu selon l'état
            when {
                isLoading -> {
                    CircularProgressIndicator() // Indicateur de chargement
                }

                errorMessage != null -> {
                    Text(text = errorMessage ?: "Une erreur est survenue")
                }

                else -> {
                    // Affichage de la liste des pays
                    countriesList.forEach { country ->
                        Text(text = "${country.name.common} - ${country.region}")
                    }
                }
            }
        }
    }
}