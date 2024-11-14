package fr.ccm2.projetm2prgd.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.ccm2.projetm2prgd.R
import fr.ccm2.projetm2prgd.ui.model.CreditCardUi
import fr.ccm2.projetm2prgd.ui.viewmodel.CreditCardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
){
    val context = LocalContext.current
    val viewModel: CreditCardViewModel = viewModel()
    val list = viewModel.creditCards.collectAsState(emptyList()).value
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = LocalContext.current.getString(R.string.creditCardScreen))  },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row {
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text(text = LocalContext.current.getString(R.string.addCreditCard)) },
                    onClick = {viewModel.insertNewCreditCard()}
                )
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text(text = LocalContext.current.getString(R.string.removeAllCreditCards)) },
                    onClick = {viewModel.deleteAllCreditCards()}
                )
            }
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background,
        ) {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(list) { item ->
                    when (item) {
                        is CreditCardUi.Header ->
                            OutlinedCard(
                                modifier = Modifier.fillParentMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.displaySmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                        is CreditCardUi.Item -> OutlinedCard(
                            modifier = Modifier.fillParentMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "${context.getString(R.string.cardNumber)}: ${item.number}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "date d'expiration: ${item.expiryDate}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        is CreditCardUi.Footer ->
                            OutlinedCard(
                                modifier = Modifier.fillParentMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "${context.getString(R.string.totalCards)} : ${item.footer}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        is CreditCardUi.FooterTotal -> OutlinedCard(
                            modifier = Modifier.fillParentMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${context.getString(R.string.totalCardsGenerated)} : ${item.footerTotal}",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}