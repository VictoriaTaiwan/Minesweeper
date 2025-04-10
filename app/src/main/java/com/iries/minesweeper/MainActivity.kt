package com.iries.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iries.minesweeper.core.Point
import com.iries.minesweeper.core.generateBoard
import com.iries.minesweeper.core.generateMines
import com.iries.minesweeper.ui.theme.MinesweeperTheme
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}


fun createTextBoard(): String {
    val board = generateBoard(10, 10)

    val startPoint = Point(2, 1)
    val mines = generateMines(startPoint, board!!, 40)

    val text = StringBuilder()
    for (i in 0..<board.columns) { // for each map's column
        text.append("\r\n")
        for (j in 0..<board.rows) {
            if (mines?.any { p -> p.x == j && p.y == i } == true) text.append(" \uD83D\uDCA3 ")
            else {
                val safePointMessage =
                    if (startPoint.x == j && startPoint.y == i) (" \uD83D\uDEA9 ") else (" \uD83D\uDD32 ")
                text.append(safePointMessage)
            }
        }
        text.append("\r\n")
    }
    return text.toString()
}