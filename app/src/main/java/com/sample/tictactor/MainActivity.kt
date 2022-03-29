package com.sample.tictactor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.tictactor.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {

    private val ticTacToeViewModel by viewModels<TicTacToeViewModel>()
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android", ticTacToeViewModel)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Greeting(name: String, ticTacToeViewModel: TicTacToeViewModel) {
   // DrawTicTacToe(shape = CircleShape)
    LazyVerticalGridDemo(shape = CircleShape, ticTacToeViewModel)
    //Text(text = "Hello $name!")
}

@ExperimentalFoundationApi
@Composable
fun LazyVerticalGridDemo(shape: Shape, ticTacToeViewModel: TicTacToeViewModel){
    val list = (1..9).map { it.toString() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxHeight()
    ) {
        Row() {
            Text(text = "Tic Tac Toe in ",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center)

            Text(text = "Compose",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontSize = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center)
        }


        LazyVerticalGrid(
            cells = GridCells.Fixed(3),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 2.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(list.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(12.dp)
                            .clip(shape)
                            .background(Color.Green)
                            .clickable {
                                ticTacToeViewModel.play(index)
                            }

                    ) {
                        Text(
                            text = ticTacToeViewModel.board[index],
                            fontWeight = FontWeight.Bold,
                            fontSize = 70.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding( start = 23.dp,
                                top = 8.dp,
                                end = 15.dp,
                                bottom = 15.dp)
                        )
                    }
                }
            }
        )


        if (ticTacToeViewModel.isGameOver) {

            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Text(text = "Game Over")
                },
                text = {
                    Text("${ticTacToeViewModel.winner}")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            ticTacToeViewModel.isGameOver = false
                        }) {
                        Text("OK")
                    }
                }
            )
        }

        Button(onClick = ticTacToeViewModel::reset,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
            Text(
                text = "Restart",
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }


}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeTheme {
        val ticTacToeViewModel = TicTacToeViewModel()
        Greeting("Android", ticTacToeViewModel)
    }
}