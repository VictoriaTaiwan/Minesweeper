package com.iries.minesweeper

import com.iries.minesweeper.core.Cell
import com.iries.minesweeper.core.generateBoard
import com.iries.minesweeper.core.generateMines
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class BoardGeneratorTest {
    @Test
    fun board_generation_isCorrect() {
        // Check that creation of the board with 0 rows and 0 columns is impossible
        assertThrows(IllegalArgumentException::class.java) {
            generateBoard(0, 0)
        }

        // Check that rows number is 5
        val board = generateBoard(5, 4)
        assertEquals(board.rows, 5)

        val startCell = Cell(2, 1)
        val minesNumber = 10
        val mines = generateMines(startCell, board, minesNumber)
        // Check that mines number is 10
        assertEquals(mines.size, 10)

        // Check that mines creation with a negative starting point is impossible.
        val startCellToFail = Cell(-2, 1)
        assertThrows(IllegalArgumentException::class.java) {
            generateMines(startCellToFail, board, minesNumber)
        }

        val minesNumToFail = 0
        assertThrows(IllegalArgumentException::class.java) {
            generateMines(startCell, board, minesNumToFail)
        }
    }
}