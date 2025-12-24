package com.example.hotelbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.hotelbooking.ui.theme.HotelBookingTheme

class ExamPractice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelBookingTheme {
                ExamPracticeMain()
            }
        }
    }
}

@Composable
fun ExamPracticeMain() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {

        composable("home") {
            Home(navController)
        }

        composable("result/{subject}") {
            val subject = it.arguments?.getString("subject") ?: ""
            ResultScreen(subject)
        }

        composable("message") {
            MessageScreen()
        }
    }
}

@Composable
fun Home(navController: NavController) {

    var subject by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Subject") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = hours,
            onValueChange = { hours = it },
            label = { Text("Study Hours") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = confirm,
                onCheckedChange = { confirm = it }
            )
            Text("Confirm Plan")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val h = hours.toIntOrNull() ?: 0
                if (h > 0 && confirm) {
                    navController.navigate("result/$subject")
                } else {
                    navController.navigate("message")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun ResultScreen(subject: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Subject: $subject",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun MessageScreen() {
    Box(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Please enter valid details and confirm the plan",
            color = MaterialTheme.colorScheme.error,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    HotelBookingTheme {
        ExamPracticeMain()
    }
}