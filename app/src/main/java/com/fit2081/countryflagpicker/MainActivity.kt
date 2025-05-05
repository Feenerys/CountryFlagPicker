package com.fit2081.countryflagpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fit2081.countryflagpicker.ui.theme.CountryFlagPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryFlagPickerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CountryFlagPickerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CountryFlagPickerScreen(modifier: Modifier) {
    var countryCode by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("")}
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Input field for the country code (e.g., "us", "ca", "au")
        TextField(
            value = countryCode,
            onValueChange = { countryCode = it },
            label = { Text("Country Code") },
            modifier = Modifier.padding(16.dp)
        )

        // Button to generate the flag URL and trigger image loading
        Button(
            onClick = {
                imageUrl = "https://flagcdn.com/w640/$countryCode.jpg"
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Get Flag")
        }

        if(imageUrl.isNotEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Country FLag",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}


