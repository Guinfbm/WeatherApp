package com.ifpe.weatherapp

import androidx.compose.runtime.setValue
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifpe.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage()
                }
            }
        }
    }
}

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {

    val modifier = modifier.fillMaxWidth(fraction = 0.9f)
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var repetirSenha by rememberSaveable { mutableStateOf("") }


    val activity = LocalActivity.current as Activity

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Criação de conta",
            fontSize = 24.sp
        )
        OutlinedTextField(
            value = nome,
            label = { Text(text = "Digite seu nome") },
            modifier = modifier,
            onValueChange = { nome = it }
        )
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = modifier,
            onValueChange = { email = it }
        )
        OutlinedTextField(
            value = senha,
            label = { Text(text = "Digite sua senha") },
            modifier = modifier,
            onValueChange = { senha = it },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = repetirSenha,
            label = { Text(text = "Repita sua senha") },
            modifier = modifier,
            onValueChange = { repetirSenha = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (senha != repetirSenha) {
                        Toast.makeText(activity, "Senhas não conferem!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(activity, "Conta criada com sucesso!", Toast.LENGTH_LONG).show()
                        activity.finish()
                    }
                },
                enabled = nome.isNotEmpty() &&
                        email.isNotEmpty() &&
                        senha.isNotEmpty() &&
                        repetirSenha.isNotEmpty()
            ) {
                Text("Registrar")
            }
            Button(
                onClick = {
                    nome = ""
                    email = ""
                    senha = ""
                    repetirSenha = ""
                },
            ) {
                Text("Limpar")
            }
        }
    }
}
