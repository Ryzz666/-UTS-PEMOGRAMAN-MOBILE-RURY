package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.myapplication.data.MenuData
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "MENU KASUNDAAN", 
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 4.sp,
                        color = Color(0xFF1B5E20)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Kembali", tint = Color(0xFF1B5E20))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White.copy(alpha = 0.9f))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        "Sajian Bumi Parahyangan",
                        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold),
                        color = Color(0xFF3E2723)
                    )
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(4.dp)
                            .padding(vertical = 12.dp)
                            .background(Color(0xFF4CAF50), RoundedCornerShape(2.dp))
                    )
                }
            }

            itemsIndexed(MenuData.menuList) { index, menu ->
                SundaMenuRow(
                    menu = menu, 
                    isEven = index % 2 == 0,
                    onClick = { navController.navigate("detail/${menu.id}") }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun SundaMenuRow(menu: com.example.myapplication.data.MenuItem, isEven: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clickable { onClick() }
            .padding(horizontal = 20.dp)
    ) {
        // Decorative Leaf-like background
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.7f)
                .align(if (isEven) Alignment.BottomStart else Alignment.BottomEnd),
            color = Color(0xFFC8E6C9).copy(alpha = 0.4f),
            shape = RoundedCornerShape(
                topStart = if (isEven) 0.dp else 40.dp,
                topEnd = if (isEven) 40.dp else 0.dp,
                bottomStart = if (isEven) 0.dp else 40.dp,
                bottomEnd = if (isEven) 40.dp else 0.dp
            )
        ) {}

        // Menu Image
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.9f)
                .align(if (isEven) Alignment.TopEnd else Alignment.TopStart),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp
        ) {
            AsyncImage(
                model = menu.imageUrl.toIntOrNull() ?: menu.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.sunda)
            )
        }

        // Info Badge
        Surface(
            modifier = Modifier
                .align(if (isEven) Alignment.CenterStart else Alignment.CenterEnd)
                .padding(horizontal = 4.dp),
            color = Color.White,
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = menu.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )
                Text(
                    text = "Rp ${menu.price / 1000}rb",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF795548),
                    fontWeight = FontWeight.Medium
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = menu.rating.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2723)
                    )
                }
            }
        }
    }
}
