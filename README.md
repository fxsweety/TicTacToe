# TicTacToe

Open the TicTacToeScreen class
Remove the Text inside the function TicTacToeScreen()
Create a GridView -> LazyVerticalGrid
Set Fixed cell size (3) 
Contents -> within the VerticalGridScope, consume the viewmodel data and print


Viewmodel data:
1. ticTacToeViewModel.play(index) is called when we click on a cell -> updates the index with X (ticTacToeViewModel.board[index])and also computes where the computer needs to enter O
2. ticTacToeViewModel.isGameOver -> checks if the game is over
3. ticTacToeViewModel.winner -> lets u know who is the winner 
