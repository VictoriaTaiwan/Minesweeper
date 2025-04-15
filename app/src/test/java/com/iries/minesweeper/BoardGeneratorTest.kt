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

        val boardRows = 10000
        val boardColumns = 10000
        val board = generateBoard(boardRows, boardColumns)
        assertEquals(board.rows, boardRows)

        val startCell = Cell(2, 1)
        val minesNumber = 9999
        val mines = generateMines(startCell, board, minesNumber)
        assertEquals(mines.size, minesNumber)
    }
}