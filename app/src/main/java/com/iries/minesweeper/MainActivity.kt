package com.iries.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.iries.minesweeper.core.Cell
import com.iries.minesweeper.core.generateBoard
import com.iries.minesweeper.core.generateMines
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println(createTextBoard())

        // Example of a simple text based UI for the game
        /*
        enableEdgeToEdge()
        setContent {
            MinesweeperTheme {
                val boardText = remember { mutableStateOf("Click the button to start!") }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        content = { Text("Generate Board") },
                        onClick = { boardText.value = "" }
                    )

                    Text(
                        text = boardText.value,
                        modifier = Modifier.padding(16.dp)
                    )

                    if (boardText.value.isBlank()) {
                        boardText.value = createTextBoard()
                    }
                }
            }
        }
        */
    }
}


fun createTextBoard(): String {
    val board = generateBoard(10, 10)

    val startCell = Cell(5, 5)
    val mines = generateMines(startCell, board, 20)

    val text = StringBuilder()
    for (i in 0..<board.columns) { // for each map's column
        text.append("\r\n")
        for (j in 0..<board.rows) {
            if (mines.any { p -> p.x == j && p.y == i }) text.append(" \uD83D\uDCA3 ")
            else {
                val safePointMessage =
                    if (startCell.x == j && startCell.y == i) (" \uD83D\uDEA9 ") else (" \uD83D\uDD32 ")
                text.append(safePointMessage)
            }
        }
        text.append("\r\n")
    }
    return text.toString()
}
