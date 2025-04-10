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

    if (minesNum < minMinesNumber || minesNum >= board.rows * board.columns) {
        println("Mines number can't be below 1, bigger than board size or equals to it. " +
                "Please, enter correct amount of mines.")
        return null
    } else if (startPoint.x >= board.rows || startPoint.y >= board.columns) {
        println("Start point is out of bounds. Please, enter correct start point.")
        return null
    }

    val minesList: ArrayList<Point> = ArrayList()
    val allPoints: ArrayList<Point> = ArrayList()

    for (i in 0..<board.columns) { // for each map's column
        for (j in 0..<board.rows) {
            if (!(startPoint.x == j && startPoint.y == i))
                allPoints.add(Point(j, i))
        }
    }

    for (i in 0..<minesNum) {
        var isComplete = false
        var point: Point? = null
        while (!isComplete) {
            val index = Random.nextInt(0, allPoints.size)
            point = allPoints[index]
            if (!minesList.any { p -> p.x == point.x && p.y == point.y })
                isComplete = true
        }
        if (point != null) {
            allPoints.remove(point)
            minesList.add(point)
        }
    }

    return minesList
}