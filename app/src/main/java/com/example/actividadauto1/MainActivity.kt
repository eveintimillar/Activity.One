package com.example.actividadauto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.actividadauto1.ui.theme.ActividadAuto1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActividadAuto1Theme {
                SurveyApp()
            }
        }
    }
}

@Composable
fun SurveyApp() {
    var currentScreen by remember { mutableStateOf("inicio") }
    when (currentScreen) {
        "inicio" -> WelcomeScreen { currentScreen = "form" }
        "form" -> FormScreen { currentScreen = "thank_you" }
        "thank_you" -> ThankYouScreen()
    }
}

@Composable
fun WelcomeScreen(onStartClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¡Bienvenido a nuestra encuesta!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onStartClicked) {
            Text(text = "Comenzar")
        }
    }
}

@Composable
fun FormScreen(onSubmitClicked: () -> Unit) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf("") }
    var maritalStatus by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Por favor, completa la encuesta", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuDemo(
            selectedOption = age,
            onOptionSelected = { age = it },
            options = listOf("menos de 18 años", "entre 18 y 22 años", "entre 23 y 30 años", "30 años o más"),
            label = "Edad"
        )
        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuDemo(
            selectedOption = maritalStatus,
            onOptionSelected = { maritalStatus = it },
            options = listOf("soltero/a", "casado/a", "divorciado/a", "viudo/a"),
            label = "Estado Civil"
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onSubmitClicked) {
            Text(text = "Enviar")
        }
    }
}

@Composable
fun DropdownMenuDemo(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    options: List<String>,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            Button(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = if (selectedOption.isEmpty()) "Seleccione una opción" else selectedOption)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ThankYouScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¡Gracias por permitirnos conocer a nuestros usuarios, que tengas un buen día!", style = MaterialTheme.typography.headlineLarge)
    }
}