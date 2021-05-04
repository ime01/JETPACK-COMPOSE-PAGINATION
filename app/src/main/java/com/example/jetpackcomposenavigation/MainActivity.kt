package com.example.jetpackcomposenavigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposenavigation.ui.theme.JetPackComposeNavigationTheme
import androidx.navigation.compose.navigate
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.jetpackcomposenavigation.data.network.IPhotoRepository
import com.example.jetpackcomposenavigation.data.network.RetrofitBuilder
import dev.chrisbanes.accompanist.picasso.PicassoImage

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        setContent {
            JetPackComposeNavigationTheme {
//              ComposeNavigation()

                PhotoUI(viewModel)
            }
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this,
            ViewModelFactory(IPhotoRepository(RetrofitBuilder.apiService),))[MainViewModel::class.java]
    }
}

@Composable
fun PhotoUI(viewModel: MainViewModel){

    val photos = viewModel.getPhotoPagination().collectAsLazyPagingItems()

    LazyColumn (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){

//        photos.apply {
//            when{
//                loadState.refresh is LoadState.Loading-> item{
//                    showProgress()
//                }
//                loadState.append is LoadState.Loading ->{
//                    item{ showProgress()}
//                }
//            }
//        }

        items(photos) { photo ->

            Box {
               photo?.downloadUrl?.let {
                   PicassoImage(data= it,
                       contentDescription = "image from picasso",
                       modifier = Modifier
                           .fillMaxWidth()
                           .fillMaxHeight(),
                       loading = {
                           Box(Modifier.matchParentSize()){
                               CircularProgressIndicator(Modifier.align(Alignment.Center))
                           }

                       },
                       error = {
                           Image(painter = painterResource(id = R.drawable.ic_baseline_error_outline_24),
                               contentDescription = "Error image")
                       },
                       fadeIn = true

                   )


//                   CoilImage(
//                       model = it,
//                       modifier = Modifier
//                           .fillMaxWidth()
//                           .border(
//                               width = 4.dp,
//                               color = Color.White,
//                               shape = RoundedCornerShape(0.dp)
//                           )
//                   )
               }
                BasicText(
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    text = "Clicked by: " + photo?.author.toString(),
                    modifier = Modifier
                        .padding(top = 8.dp, start = 4.dp)
                        .background(color = Color.White)
                        .padding(4.dp)
                )
            }


        }
    }
}

fun showProgress() {

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
