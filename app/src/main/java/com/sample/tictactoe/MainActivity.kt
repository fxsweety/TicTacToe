package com.sample.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi

import com.sample.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {

    private val ticTacToeViewModel by viewModels<TicTacToeViewModel>()
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                TicTacToeScreen(ticTacToeViewModel)
            }
        }
    }
}

