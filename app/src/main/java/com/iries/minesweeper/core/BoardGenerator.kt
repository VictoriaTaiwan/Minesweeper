package com.iries.minesweeper.core

import kotlin.random.Random

private const val minBoardSize = 2
private const val minMinesNumber = 1

fun generateBoard(rows: Int, columns: Int): Board {
    val boardSize = rows * columns
    // Check that board size isn't less than 2
    if (boardSize < minBoardSize) {
        throw (Exception(
            "Map size can't be less than 2. Please, enter correct map size."
        ))
    }
    println("Board with $rows rows and $columns columns was generated.")
    return Board(rows, columns)
}

fun generateMines(startPoint: Point, board: Board, minesNum: Int): ArrayList<Point> {
    val boardSize = board.rows * board.columns
    // Check that board size isn't less than 2
    if (boardSize < minBoardSize) {
        throw (Exception(
            "Map size can't be less than 2. Please, enter correct map size."
        ))
    }
    // Check that mines number isn't less than 1 and doesn't exceed board's size.
    if (minesNum < minMinesNumber || minesNum >= board.rows * board.columns) {
        throw (Exception(
            "Mines number can't be below 1, bigger than board size or equals to it. " +
                    "Please, enter correct amount of mines."
        ))
    }
    // Check that the start point doesn't exceed board's size and isn't negative.
    if (startPoint.x >= board.rows || startPoint.y >= board.columns
        || startPoint.x < 0 || startPoint.y < 0
    ) {
        throw (Exception("Start point is out of bounds. Please, enter correct start point."))
    }

    val minesList: ArrayList<Point> = ArrayList()
    val potentialMinePoints: ArrayList<Point> = ArrayList()

    // Populate list of all points except the starting point as potential mine slots
    for (i in 0..<board.columns) { // for each board's column
        for (j in 0..<board.rows) { // for each board's row
            if (!(startPoint.x == j && startPoint.y == i)) // skip the start point
                potentialMinePoints.add(Point(j, i))
        }
    }

    // create a random mine slot excluding already checked slots
    for (i in 0..<minesNum) {
        var point: Point?
        val index = Random.nextInt(0, potentialMinePoints.size) // get a random index
        point = potentialMinePoints[index]
        potentialMinePoints.remove(point) // remove the point from potential mines list
        minesList.add(point) // add the point to the actual mines list
    }

    return minesList
}