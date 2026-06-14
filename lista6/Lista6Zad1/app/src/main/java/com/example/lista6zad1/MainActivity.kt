package com.example.lista6zad1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WebsitesApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebsitesApp() {
    val websites = listOf(
        "https://www.google.com",
        "https://developer.android.com",
        "https://kotlinlang.org"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ulubione strony") }
            )
        }
    ) { innerPadding ->
        WebsiteList(
            websites = websites,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun WebsiteList(
    websites: List<String>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(websites) { url ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = url,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Kliknij, aby otworzyć w przeglądarce",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}