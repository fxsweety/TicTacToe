package com.sample.tictactor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sample.tictactor.TicTacToeUtil.PLAYER_O
import com.sample.tictactor.TicTacToeUtil.PLAYER_X
import com.sample.tictactor.TicTacToeUtil.isBoardFull
import com.sample.tictactor.TicTacToeUtil.isGameWon

class TicTacToeViewModel :ViewModel() {

    var singlePlayer by mutableStateOf(true)
        private set

    var isGameOver by mutableStateOf(false)

    var winner by mutableStateOf("")
        private set

    var board by mutableStateOf(arrayListOf("", "", "", "", "", "", "", "", ""))
        private set

    var currentPlayer = PLAYER_X

    var player = if (currentPlayer == PLAYER_X) "You" else "Computer"

    fun play(move: Int) {
        if (isGameOver) return

        if (board[move] == "") {
            if (currentPlayer == PLAYER_X) {
                board = ArrayList(board.toMutableList().also {
                    it[move] = PLAYER_X
                })
                currentPlayer = PLAYER_O

                if (singlePlayer) {
                    if (!isBoardFull(board) && !isGameWon(board, PLAYER_X)) {
                        val nextMove = TicTacToeUtil.computerMove(board)

                        board = ArrayList(board.toMutableList().also {
                            it[nextMove] = PLAYER_O
                        })
                    }
                    currentPlayer = PLAYER_X
                }

            } else {
                board = ArrayList(board.toMutableList().also {
                    it[move] = PLAYER_O
                })
                currentPlayer = PLAYER_X

                if (singlePlayer) {
                    if (!isBoardFull(board) && !isGameWon(board, PLAYER_O)) {
                        val nextMove = TicTacToeUtil.computerMove(board)

                        board = ArrayList(board.toMutableList().also {
                            it[nextMove] = PLAYER_X
                        })
                    }
                    currentPlayer = PLAYER_O
                }
            }
        }


        //calculate and show game result
        isGameOver = isGameWon(board, PLAYER_X) || isGameWon(board, PLAYER_O) || isBoardFull(board)
        winner = TicTacToeUtil.gameResult(board, singlePlayer)

        Log.d(TAG, "$board")
    }

    fun reset() {
        isGameOver = false
        board = arrayListOf("", "", "", "", "", "", "", "", "")
    }

    fun updatePlayerMode(singlePlayer: Boolean) {
        reset()
        this.singlePlayer = singlePlayer
    }


    companion object {
        const val TAG = "TicTacToeViewModel"
    }
}