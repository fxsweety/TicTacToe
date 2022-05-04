package com.sample.tictactoe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.tictactoe.TicTacToeUtil.PLAYER_X
import com.sample.tictactoe.ui.theme.TicTacToeTheme
import kotlinx.coroutines.channels.ticker

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TicTacToeScreen(ticTacToeViewModel: TicTacToeViewModel) {
    val player = remember{mutableStateOf(getPlayerName(ticTacToeViewModel))}

    Column(
        Modifier
            .background(color = Color.Yellow)
            .fillMaxHeight()
            .fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        if (!ticTacToeViewModel.isGameOver) {
            Text(
                "Let's Play Tic Tac Toe",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Row(
                // below line is used for specifying
                // vertical arrangement.
                verticalAlignment = Alignment.CenterVertically,

                // below line is used for specifying
                // horizontal arrangement.
                horizontalArrangement = Arrangement.Center,
            ) {
                Checkbox(
                    checked = ticTacToeViewModel.singlePlayer,
                    onCheckedChange = {
                        // when user clicks the button
                        // we reset the game
                        ticTacToeViewModel.currentPlayer = PLAYER_X
                        ticTacToeViewModel.updatePlayerMode(it)
                        player.value = getPlayerName(ticTacToeViewModel)
                    }
                )

                Text(
                    "Single Player",
                    color = Color.Black
                )
            }
            Text(
                "Player's Turn: " + player.value,
                color = Color.Blue
            )
        } else {
            Text(
                ticTacToeViewModel.winner,
                color = Color.Black
            )
            Button(
                onClick = {
                // when user clicks the button
                // we reset the game
                    ticTacToeViewModel.currentPlayer = PLAYER_X

                    ticTacToeViewModel.reset()

                    player.value = getPlayerName(ticTacToeViewModel)
                }
            ) {
                Text("Play Again")
            }
        }
        LazyVerticalGrid(
            // 3x3 Grid
            cells = GridCells.Fixed(3)
        ) {
            itemsIndexed(ticTacToeViewModel.board) { index, text ->
                Square(text, onPlay = {
                    // when user is clicking the button
                    // we are making a move
                    ticTacToeViewModel.play(index)
                    player.value = getPlayerName(ticTacToeViewModel)
                })
            }
        }
    }
}

@Composable
fun Square(text: String, onPlay: () -> Unit) {
    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            //modifier = Modifier.padding(10.dp),

            // below line is use to add onclick
            // parameter for our button onclick
            onClick = onPlay,

            // in below line we are using modifier
            // which is use to add padding to our button
            //modifier = Modifier.padding(all = Dp(10F)),

            // below line is use to set or
            // button as enable or disable.
            enabled = true,

            // below line is use to
            // add border to our button.
            border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black)),

            // Make the button rectangular
            shape = RectangleShape
        )
        // below line is use to
        // add text on our button
        {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    TicTacToeTheme {
        val ticTacToeViewModel = TicTacToeViewModel()
        TicTacToeScreen(ticTacToeViewModel)
    }
}

fun getPlayerName(ticTacToeViewModel: TicTacToeViewModel): String {
    if (ticTacToeViewModel.singlePlayer) {
        return "${ticTacToeViewModel.player} (${ticTacToeViewModel.currentPlayer})"
    }

    return ticTacToeViewModel.currentPlayer
}