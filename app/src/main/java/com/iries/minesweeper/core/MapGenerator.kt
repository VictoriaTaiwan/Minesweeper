package com.iries.minesweeper.core

import kotlin.random.Random

fun generateMap(startPoint: Point, mapBounds: Point, minesNum: Int): ArrayList<Point>? {

    // Handling special cases where map generation is impossible
    val mapSize = mapBounds.x * mapBounds.y
    if (mapSize < 2) {
        println("Map size can't be less than 2. Please, enter correct map size.")
        return null
    } else if (minesNum < 1) {
        println("Mines number can't be below 1. Please, enter correct amount of mines.")
        return null
    } else if (startPoint.x >= mapBounds.x || startPoint.y >= mapBounds.y) {
        println("Start point is out of bounds. Please, enter correct start point.")
        return null
    }

    val maxSafeArea = mapSize - minesNum
    var checkedCellNum = 0
    var currentMinesNum = 0
    val minesList: ArrayList<Point> = ArrayList()

    mapLoop@ for (i in 0..<mapBounds.y) { // for each map's column
        for (j in 0..<mapBounds.x) { // for each colum's row
            if (currentMinesNum == minesNum) {
                println("All mines are generated")
                break@mapLoop
            }
            checkedCellNum++
            if (!(startPoint.y == i && startPoint.x == j)) {
                // if safe area size is maxed or it's a mine cell
                if (checkedCellNum >= maxSafeArea || Random.nextBoolean()) {
                    currentMinesNum++
                    minesList.add(Point(j, i))
                    println("Mine ($j, $i)")
                }
            }
        }
    }

    for (i in 0..mapBounds.y) { // for each map's column
        println(" ")
        for (j in 0..<mapBounds.x) {
            if (minesList.any { p -> p.x == j && p.y == i }) print(" Mine ")
            else {
                if (startPoint.x == j && startPoint.y == i)
                    print(" Start ")
                else print(" Safe ")
            }
        }
    }

    return minesList
}