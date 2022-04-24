package com.sample.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sample.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun TicTacToeScreen(ticTacToeViewModel: TicTacToeViewModel) {
    Column(Modifier.background(color = Color.Yellow)
                    .fillMaxHeight().fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Text("Let's play",
              color = Color.Black)
        Text("Tic Tac Toe",
            color = Color.Blue)
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