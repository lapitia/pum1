package com.example.lista5_zad1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista5_zad1.ui.theme.Lista5_zad1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista5_zad1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterExample(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableIntStateOf(0) }
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = counter.toString(),
                fontSize = 120.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { counter++ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Count up")
                }

                Button(
                    onClick = { counter-- },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Count down")
                }

                Button(
                    onClick = { counter = 0 },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reset")
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = counter.toString(),
                fontSize = 180.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { counter++ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Count up")
            }

            Button(
                onClick = { counter-- },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Count down")
            }

            Button(
                onClick = { counter = 0 },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    Lista5_zad1Theme {
        CounterExample()
    }
}