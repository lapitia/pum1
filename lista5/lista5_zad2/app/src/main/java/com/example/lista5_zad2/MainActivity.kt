package com.example.lista5_zad2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista5_zad2.ui.theme.Lista5_zad2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista5_zad2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorExample(modifier: Modifier = Modifier) {
    var number1 by rememberSaveable { mutableStateOf("") }
    var number2 by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("Wynik: ") }

    val orientation = LocalConfiguration.current.orientation

    fun calculate(operator: Char) {
        val a = number1.toIntOrNull()
        val b = number2.toIntOrNull()

        result = when {
            a == null || b == null -> "Wpisz dwie liczby całkowite"
            operator == '/' && b == 0 -> "Nie można dzielić przez 0"
            operator == '+' -> "Wynik: ${a + b}"
            operator == '-' -> "Wynik: ${a - b}"
            operator == '*' -> "Wynik: ${a * b}"
            operator == '/' -> "Wynik: ${a.toDouble() / b.toDouble()}"
            else -> "Błąd"
        }
    }

    @Composable
    fun Inputs() {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = number1,
                onValueChange = { number1 = it },
                label = { Text("Liczba 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = number2,
                onValueChange = { number2 = it },
                label = { Text("Liczba 2") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = result,
                fontSize = 28.sp
            )
        }
    }

    @Composable
    fun Buttons() {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { calculate('+') },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+")
                }

                Button(
                    onClick = { calculate('-') },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("-")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { calculate('*') },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("*")
                }

                Button(
                    onClick = { calculate('/') },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("/")
                }
            }
        }
    }

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Inputs()
            }

            Column(modifier = Modifier.weight(1f)) {
                Buttons()
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Inputs()
            Buttons()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    Lista5_zad2Theme {
        CalculatorExample()
    }
}