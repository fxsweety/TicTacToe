# TicTacToe

When you first build the app, you would see the below screen


![start](https://user-images.githubusercontent.com/7736034/232597684-619556db-e5d5-4ffa-9b1e-68f3311da955.png)

Now we work on changing the view to Tic Tac Toe view:
Open the TicTacToeScreen class
Remove the Text inside the function TicTacToeScreen()
Create a GridView -> LazyVerticalGrid
Set Column = Fixed cell size (3) 
Contents -> within the VerticalGridScope, consume the viewmodel data and print


{code}

    val list = (1..9).map{it.toString()}
       LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
            items(list.size) { index ->
                Button(modifier = Modifier.padding(4.dp), onClick = {
                    //your onclick code here
                }) {
                    Text(text = list[index],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp))
                }
            }
            })
            
       
 // this will create a Gridview printing numbers 1 to 9
<img width="370" alt="tictactoe_grid" src="https://user-images.githubusercontent.com/7736034/231599772-9f975aba-9784-4a10-8e9f-90e7e9c22f49.png">

Now we need to work on click to update as X and text to display the value

Viewmodel data:
1. ticTacToeViewModel.play(index) is called when we click on a cell -> updates the index with X (ticTacToeViewModel.board[index])and also computes where the computer needs to enter O
2. ticTacToeViewModel.isGameOver -> checks if the game is over
3. ticTacToeViewModel.winner -> lets u know who is the winner 

        
        //Alert Dialog for endresult:
          if (ticTacToeViewModel.isGameOver) {
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Text(text = "TicTacToe Winner")
                },
                text = {
                    Text("${ticTacToeViewModel.winner} ")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            ticTacToeViewModel.reset()
                        }) {
                        Text("Play Again")
                    }
                }
            )
        }
