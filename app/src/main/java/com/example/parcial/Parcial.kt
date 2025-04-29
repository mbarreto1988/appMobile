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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BilleteraView(navController: NavController) {
    var saldo by remember { mutableIntStateOf(10000) }
    var retiro by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val spaceModifier = Modifier.height(10.dp)
    val columModifier = Modifier.fillMaxSize().padding(20.dp)
    val columVertical = Arrangement.Center
    val columHorizontal = Alignment.CenterHorizontally

    Column( columModifier, columVertical, columHorizontal ) {
        Text( "Saldo disponible: $${saldo}", style = MaterialTheme.typography.headlineSmall )
        Spacer( spaceModifier )
        TextField(
            value = retiro,
            onValueChange = { retiro = it },
            label = { Text("Monto a retirar") },
            singleLine = true
        )
        Spacer( spaceModifier )
        Button(
            onClick = {
                val monto = retiro.toIntOrNull()
                if ( monto != null && monto > 0 && monto <= saldo ) {
                    saldo -= monto
                    navController.navigate("comprobante/$monto/$saldo")
                } else {
                    error = "Monto inválido o insuficiente"
                }
            }
        ) {
            Text( "Retirar" )
        }
        Spacer( spaceModifier )
        Text( text = error, color = Color.Red )
    }
}

@Composable
fun BilleteraNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            BilleteraView( navController )
        }
        composable("comprobante/{monto}/{saldo}") { backStackEntry ->
            val monto = backStackEntry.arguments?.getString("monto") ?: "0"
            val saldo = backStackEntry.arguments?.getString("saldo") ?: "0"
            ComprobanteView(monto, saldo, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BilleteraViewPreview() {
    val navController = rememberNavController()
    BilleteraView( navController = navController )
}