package com.iries.minesweeper

import com.iries.minesweeper.core.Point
import com.iries.minesweeper.core.generateBoard
import com.iries.minesweeper.core.generateMines
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class BoardGeneratorTest {
    @Test
    fun board_generation_isCorrect() {
        // Check that creation of the board with 0 rows and 0 columns is impossible
        assertThrows(Exception::class.java) {
            generateBoard(0,0)
        }

        // Check that rows number is 5
        val board = generateBoard(5, 4)
        assertEquals(board.rows, 5)

        val startPoint = Point(2, 1)
        val minesNumber = 10
        val mines = generateMines(startPoint, board, minesNumber)
        // Check that mines number is 10
        assertEquals(mines.size, 10)

        // Check that mines creation with a negative starting point is impossible.
        val startPointToFail = Point(-2, 1)
        assertThrows(Exception::class.java) {
            generateMines(startPointToFail, board, minesNumber)
        }
    }
}