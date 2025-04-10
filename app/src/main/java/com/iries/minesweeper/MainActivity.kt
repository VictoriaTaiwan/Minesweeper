package com.iries.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.iries.minesweeper.core.Board
import com.iries.minesweeper.core.Point
import com.iries.minesweeper.core.generateBoard
import com.iries.minesweeper.core.generateMines
import com.iries.minesweeper.ui.theme.MinesweeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinesweeperTheme {}
        }

        val board = generateBoard(3, 2)
        if (board != null) printResults(board)
    }
}

fun printResults(board: Board) {
    val startPoint = Point(2, 1)
    val mines = generateMines(startPoint, board, 2)

    for (i in 0..board.columns) { // for each map's column
        println(" ")
        for (j in 0..<board.rows) {
            if (mines?.any { p -> p.x == j && p.y == i } == true) print(" Mine ")
            else {
                val safePointMessage =
                    if (startPoint.x == j && startPoint.y == i) (" Start ") else (" Safe ")
                print(safePointMessage)
            }
        }
    }
}