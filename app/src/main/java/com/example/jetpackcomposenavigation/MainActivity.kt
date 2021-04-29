package com.example.jetpackcomposenavigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposenavigation.ui.theme.JetPackComposeNavigationTheme
import androidx.navigation.compose.navigate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeNavigationTheme {
              ComposeNavigation()
            }
        }
    }
}

@Composable
fun ComposeNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen")
    {
        composable("first_screen"){
            FirstScreen(navController = navController)
        }

        composable("second_screen"){
            SecondScreen(navController = navController)
        }

        composable("third_screen"){
            ThirdScreen(navController = navController)
        }
    }


}

@Composable
fun ThirdScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Third Screen\n" + "Click me to go to back to FirstScreen",
            color = Color.Magenta,
            style = MaterialTheme.typography.body2 + TextStyle(fontWeight = FontWeight.Bold)+TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(24.dp)
                .clickable(onClick = {
                    // this will navigate to back to first screen
                    navController.navigate("first_screen")
                })
        )

    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Second Screen\n" + "Click me to go to Third Screen",
            color = Color.Red,
            style = MaterialTheme.typography.body2 + TextStyle(fontWeight = FontWeight.Bold)+TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(24.dp)
                .clickable(onClick = {
                    // this will navigate to third screen
                    navController.navigate("third_screen")
                })
        )

    }
}

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "First Screen\n" + "Click me to go to Second Screen",
            color = Color.Black,
            style = MaterialTheme.typography.body2 + TextStyle(fontWeight = FontWeight.Bold)+TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(24.dp)
                .clickable(onClick = {
                    // this will navigate to second screen
                    navController.navigate("second_screen")
                })
        )

    }
}
