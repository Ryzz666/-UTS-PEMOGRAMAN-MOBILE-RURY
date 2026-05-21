package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.ProfileRepository
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = ProfileRepository(context)

    var name by remember { mutableStateOf(repository.getRestaurantName()) }
    var address by remember { mutableStateOf(repository.getAddress()) }
    var description by remember { mutableStateOf(repository.getDescription()) }
    var openHours by remember { mutableStateOf(repository.getOpenHours()) }

    Scaffold(
        containerColor = Color(0xFFFFFDE7),
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "UBAH PROFIL", 
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF1B5E20)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = Color(0xFF1B5E20))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Text(
                "Informasi Restoran",
                style = MaterialTheme.typography.headlineSmall.copy(fontFamily = FontFamily.Serif),
                color = Color(0xFF3E2723)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            SundaTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nama Restoran"
            )

            Spacer(modifier = Modifier.height(16.dp))

            SundaTextField(
                value = address,
                onValueChange = { address = it },
                label = "Alamat"
            )

            Spacer(modifier = Modifier.height(16.dp))

            SundaTextField(
                value = description,
                onValueChange = { description = it },
                label = "Filosofi & Deskripsi",
                singleLine = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            SundaTextField(
                value = openHours,
                onValueChange = { openHours = it },
                label = "Jam Operasional"
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    repository.saveProfile(name, address, description, openHours)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("SIMPAN PERUBAHAN", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("BATALKAN", color = Color.Gray)
            }
        }
    }
}

@Composable
fun SundaTextField(value: String, onValueChange: (String) -> Unit, label: String, singleLine: Boolean = true) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF1B5E20),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF4CAF50),
                unfocusedBorderColor = Color(0xFF4CAF50).copy(alpha = 0.5f),
                cursorColor = Color(0xFF4CAF50)
            ),
            singleLine = singleLine,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF3E2723))
        )
    }
}
