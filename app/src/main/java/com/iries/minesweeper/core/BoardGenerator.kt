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

    val maxSafeArea = board.rows * board.columns - minesNum
    var checkedCellNum = 0
    var currentMinesNum = 0
    val minesList: ArrayList<Point> = ArrayList()

    mapLoop@ for (i in 0..<board.columns) { // for each map's column
        for (j in 0..<board.rows) { // for each colum's row
            if (currentMinesNum == minesNum) {
                println("All mines are generated.")
                break@mapLoop
            }
            checkedCellNum++
            if (!(startPoint.y == i && startPoint.x == j)) {
                // if safe area size is maxed or it's a mine cell
                if (checkedCellNum >= maxSafeArea || Random.nextBoolean()) {
                    currentMinesNum++
                    minesList.add(Point(j, i))
                    println("Mine ($j, $i) was placed to the board.")
                }
            }
        }
    }

    return minesList
}