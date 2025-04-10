package com.iries.minesweeper.core

import kotlin.random.Random

private const val minBoardSize = 2
private const val minMinesNumber = 1

fun generateBoard(rows: Int, columns: Int): Board? {
    val boardSize = rows * columns
    if (boardSize < minBoardSize) {
        println("Map size can't be less than 2. Please, enter correct map size.")
        return null
    }
    println("Board with $rows rows and $columns columns was generated.")
    return Board(rows, columns)
}

fun generateMines(startPoint: Point, board: Board, minesNum: Int): ArrayList<Point>? {

    if (minesNum < minMinesNumber) {
        println("Mines number can't be below 1. Please, enter correct amount of mines.")
        return null
    } else if (startPoint.x >= board.rows || startPoint.y >= board.columns) {
        println("Start point is out of bounds. Please, enter correct start point.")
        return null
    }

    val minesList: ArrayList<Point> = ArrayList()

    for (i in 0..<minesNum) {
        var isComplete = false
        var x = 0
        var y = 0
        while (!isComplete) {
            x = Random.nextInt(0, board.rows)
            y = Random.nextInt(0, board.columns)
            if (!minesList.any { p -> p.x == x && p.y == y }
                && x != startPoint.x && y != startPoint.y)
                isComplete = true
        }
        minesList.add(Point(x, y))
    }

    return minesList
}