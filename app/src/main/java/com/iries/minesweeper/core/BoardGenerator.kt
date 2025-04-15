package com.iries.minesweeper.core

import kotlin.random.Random

// 1 mine, 1 starting point
// but beginner's minesweeper game usually has at least 8x8 board size
private const val minBoardSize = 2

private const val minMinesNumber = 1

// Messages for exceptions
private const val incorrectBoardSizeMessage =
    "Map size can't be less than 2. Please, enter correct map size."
private const val incorrectMinesNumberMessage =
    "Mines number can't be below 1, bigger than board size or equals to it. " +
            "Please, enter correct amount of mines."
private const val cellOutOfBoundsMessage =
    "Cell is out of bounds. Please, enter correct start point."

fun generateBoard(rows: Int, columns: Int) : Board {
    val boardSize = rows * columns
    // Check that board size isn't less than 2
    if (boardSize < minBoardSize)
        throw (IllegalArgumentException(incorrectBoardSizeMessage))

    println("Board with $rows rows and $columns columns was generated.")
    return Board(rows, columns)
}

fun generateMines(startCell: Cell, board: Board, minesNum: Int): HashSet<Cell> {
    val boardSize = board.rows * board.columns
    // Check that board size isn't less than 2
    if (boardSize < minBoardSize)
        throw (IllegalArgumentException(incorrectBoardSizeMessage))

    // Check that mines number isn't less than 1 and doesn't exceed board's size.
    if (minesNum < minMinesNumber || minesNum >= board.rows * board.columns)
        throw (IllegalArgumentException(incorrectMinesNumberMessage))

    // Check that the start point doesn't exceed board's size and isn't negative.
    if (startCell.x >= board.rows || startCell.y >= board.columns
        || startCell.x < 0 || startCell.y < 0
    ) throw (IllegalArgumentException(cellOutOfBoundsMessage))

    val mines = HashSet<Cell>()
    val random = Random

    while (mines.size < minesNum) {
        val x = random.nextInt(board.rows)
        val y = random.nextInt(board.columns)
        val newMine = Cell(x, y)

        // Avoid placing a mine on the startCell and avoid duplicates
        if (newMine != startCell && !mines.contains(newMine)) {
            mines.add(newMine)
        }
    }

    return mines
}