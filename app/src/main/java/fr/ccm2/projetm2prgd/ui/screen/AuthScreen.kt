package fr.ccm2.projetm2prgd.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.ccm2.projetm2prgd.R
import fr.ccm2.projetm2prgd.firebase.viewmodel.FirebaseAuthViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun FirebaseAuthScreen(
    navController: NavController,
    viewModel: FirebaseAuthViewModel
) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val currentUser by viewModel.mCurrentUser.observeAsState()
    val errorProcess by viewModel.mErrorProcess.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = if (currentUser != null) "Connecté en tant que ${currentUser?.email}" else "Non connecté",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (currentUser != null) Color.Green else Color.Red,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Authentification",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))


        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )


        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(text = "Mot de passe") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )


        when (errorProcess) {
            11 -> Text(
                text = "Erreur : Email ou mot de passe incorrect.",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            12 -> Text(
                text = "Erreur : Un compte avec cet email existe déjà.",
                color = Color(0xFFFFA500),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                if (currentUser != null) {

                    viewModel.disconnectUser()
                    Toast.makeText(context, "Déconnecté", Toast.LENGTH_SHORT).show()
                } else {

                    viewModel.loginUser(emailState.value, passwordState.value)

                    if (currentUser != null) {

                        emailState.value = ""
                        passwordState.value = ""
                        Toast.makeText(context, "Connecté", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = if (currentUser != null) "Se déconnecter" else "Se connecter",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (currentUser == null) {
            Button(
                onClick = {
                    if (emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()) {
                        viewModel.registerNewUser(emailState.value, passwordState.value)
                        emailState.value = ""
                        passwordState.value = ""
                    } else {
                        Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = "S'inscrire",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}


