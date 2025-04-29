package com.example.parcial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ComprobanteView( monto: String, saldoRestante: String, navController: NavController ) {
    val columModifier = Modifier.fillMaxSize().padding(20.dp)
    val columVertical = Arrangement.Center
    val columHorizontal = Alignment.CenterHorizontally
    val spaceModifier = Modifier.height(10.dp)
    val fechaHora = SimpleDateFormat("MM/dd/yyyy - HH:mm:ss", Locale.getDefault()).format(Date())

    Column( columModifier, columVertical, columHorizontal ) {
        Text("Retiro exitoso", style = MaterialTheme.typography.headlineMedium)
        Spacer( spaceModifier )
        Text("Monto retirado: $$monto", style = MaterialTheme.typography.bodyLarge)
        Spacer( spaceModifier )
        Text("Saldo restante: $$saldoRestante", style = MaterialTheme.typography.bodyLarge, color = Color.Red)
        Spacer(spaceModifier)
        Text("Fecha: $fechaHora", style = MaterialTheme.typography.bodyMedium)
        Spacer(spaceModifier)
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}