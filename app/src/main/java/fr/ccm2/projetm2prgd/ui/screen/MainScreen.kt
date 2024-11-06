package fr.ccm2.projetm2prgd.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.ccm2.projetm2prgd.R

@Composable
fun MainScreen (
    onButtonLoginClick: () -> Unit,
    onButtonCountriesClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Grégory Dehame",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Pascal Rohart",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.size(24.dp))

            Button(
                onClick = { onButtonLoginClick() },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(200.dp, 50.dp),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.login),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Button(
                onClick = { onButtonCountriesClick() },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(200.dp, 50.dp),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.Countries),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        onButtonLoginClick = {},
        onButtonCountriesClick = {}
    )
}
