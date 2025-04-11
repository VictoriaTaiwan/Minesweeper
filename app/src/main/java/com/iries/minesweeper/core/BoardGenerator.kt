package com.iries.minesweeper.core

import com.iries.minesweeper.core.exceptions.BoardSizeException
import com.iries.minesweeper.core.exceptions.MinesNumberException
import com.iries.minesweeper.core.exceptions.StartPointOutOfBoundsException
import kotlin.random.Random

private const val minBoardSize = 2
private const val minMinesNumber = 1

fun generateBoard(rows: Int, columns: Int): Board {
    val boardSize = rows * columns
    // Check that board size isn't less than 2
    if (boardSize < minBoardSize) throw (BoardSizeException())

    println("Board with $rows rows and $columns columns was generated.")
    return Board(rows, columns)
}

fun generateMines(startCell: Cell, board: Board, minesNum: Int): ArrayList<Cell> {
    val boardSize = board.rows * board.columns
    // Check that board size isn't less than 2
    if (boardSize < minBoardSize) throw (BoardSizeException())

    // Check that mines number isn't less than 1 and doesn't exceed board's size.
    if (minesNum < minMinesNumber || minesNum >= board.rows * board.columns)
        throw (MinesNumberException())

    // Check that the start point doesn't exceed board's size and isn't negative.
    if (startCell.x >= board.rows || startCell.y >= board.columns
        || startCell.x < 0 || startCell.y < 0
    ) throw (StartPointOutOfBoundsException())

    val minesList: ArrayList<Cell> = ArrayList()
    val potentialMineCells: ArrayList<Cell> = ArrayList()

    // Populate list of all points except the starting point as potential mine slots
    for (i in 0..<board.columns) { // for each board's column
        for (j in 0..<board.rows) { // for each board's row
            if (!(startCell.x == j && startCell.y == i)) // skip the start point
                potentialMineCells.add(Cell(j, i))
        }
    }

    // create a random mine slot excluding already checked slots
    for (i in 0..<minesNum) {
        var cell: Cell?
        val index = Random.nextInt(0, potentialMineCells.size) // get a random index
        cell = potentialMineCells[index]
        potentialMineCells.remove(cell) // remove the point from potential mines list
        minesList.add(cell) // add the point to the actual mines list
    }

    return minesList
}