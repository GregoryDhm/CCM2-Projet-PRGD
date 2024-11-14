package fr.ccm2.projetm2prgd.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.ccm2.projetm2prgd.ui.viewmodel.AuthViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import fr.ccm2.projetm2prgd.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseAuthScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordStateVerif = remember { mutableStateOf("") }
    val currentUser by viewModel.mCurrentUser.observeAsState()
    val errorProcess by viewModel.mErrorProcess.observeAsState()
    val context = LocalContext.current
    var status by remember { mutableStateOf("login") }

    //Redirection si le user est déjà co
    if (currentUser != null) {
        LaunchedEffect(Unit) {
            navController.navigate("main_screen") {
                popUpTo("firebase_auth_screen") { inclusive = true }
            }
        }
    }

    LaunchedEffect(errorProcess) {
        var msg = ""
        when (errorProcess) {
            0 -> {
                msg = context.getString(R.string.accountCreatedMessage)
                emailState.value = ""
                passwordState.value = ""
                status = "login"
            }
            11 -> msg = context.getString(R.string.errorIncorrectEmailPassword)
            12 -> msg = context.getString(R.string.emailAlreadyExistsError)
            13-> msg = context.getString(R.string.passwordTooShortError)
            10 -> msg = context.getString(R.string.unknownError)
        }
        if(msg != "") Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = LocalContext.current.getString(R.string.loginScreen))  },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

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
                label = { Text(text = LocalContext.current.getString(R.string.password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                visualTransformation = PasswordVisualTransformation()
            )
            // Verif passwd en cas de register
            if ( status == "register"){
                TextField(
                    value = passwordStateVerif.value,
                    onValueChange = { passwordStateVerif.value = it },
                    label = { Text(text = LocalContext.current.getString(R.string.password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (currentUser == null && status == "login"){
                Button(
                    onClick = {
                        viewModel.loginUser(emailState.value, passwordState.value)
                                emailState.value = ""
                                passwordState.value = ""
                                //Toast.makeText(context, "Connecté", Toast.LENGTH_SHORT).show()
                        if (viewModel.mCurrentUser.value != null) {
                            emailState.value = ""
                            passwordState.value = ""
                            Toast.makeText(context, "Connecté", Toast.LENGTH_SHORT).show()
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
                        LocalContext.current.getString(R.string.login),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = context.getString(R.string.noAccountPrompt),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = context.getString(R.string.register),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            status = "register"
                        }
                    )
                }
            }
            if (currentUser == null && status == "register") {
                Button(
                    onClick = {
                        if (emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()) {
                            if (passwordState.value == passwordStateVerif.value) {
                                viewModel.registerNewUser(emailState.value, passwordState.value)
                            }else{
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.passwordMustBeIdentical),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.fillAllFields),
                                Toast.LENGTH_SHORT
                            ).show()
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
                        text = LocalContext.current.getString(R.string.register),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}


