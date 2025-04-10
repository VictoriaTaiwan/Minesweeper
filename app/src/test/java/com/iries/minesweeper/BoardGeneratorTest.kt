package com.iries.minesweeper

import com.iries.minesweeper.core.Point
import com.iries.minesweeper.core.generateBoard
import com.iries.minesweeper.core.generateMines
import org.junit.Assert.assertNotNull
import org.junit.Test

class BoardGeneratorTest {
    @Test
    fun board_generation_isCorrect() {
        val board = generateBoard(3, 2)
        assertNotNull(board)

        val startPoint = Point(2, 1)
        val mines = generateMines(startPoint, board!!, 2)
        assertNotNull(mines)

        for (i in 0..<board.columns) { // for each map's column
            println(" ")
            for (j in 0..<board.rows) {
                if (mines?.any { p -> p.x == j && p.y == i } == true) print(" Mine ")
                else {
                    val safePointMessage =
                        if (startPoint.x == j && startPoint.y == i) (" Start ") else (" Safe ")
                    print(safePointMessage)
                }
            }
            println(" ")
        }
    }
}