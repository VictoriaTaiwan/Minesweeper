package com.iries.minesweeper.core

import kotlin.random.Random

fun generateMap(startPoint: Point, mapSize: Point, minesNum: Int): ArrayList<Point>? {

    // Handling special cases where map generation is impossible
    val map = mapSize.x * mapSize.y
    if (map < 2) {
        println("Map size can't be less than 2. Please, enter correct map size.")
        return null
    } else if (minesNum < 1) {
        println("Mines number can't be below 1. Please, enter correct amount of mines.")
        return null
    }

    val maxSafeArea = map - minesNum - 1 // exclude startPoint
    var checkedCellNum = 0
    var currentMinesNum = 0
    val minesList: ArrayList<Point> = ArrayList()

    mapLoop@ for (i in 0..<mapSize.y) { // for each map's column
        for (j in 0..<mapSize.x) { // for each colum's row
            if (currentMinesNum == minesNum) {
                // println("All mines are generated")
                break@mapLoop
            }
            if (!(startPoint.y == i && startPoint.x == j)) {
                // if safe area size is maxed or it's a mine cell
                if (maxSafeArea == checkedCellNum || Random.nextBoolean()) {
                    currentMinesNum++
                    minesList.add(Point(j, i))
                    println("Mine ($j, $i)")
                }
            }
            checkedCellNum++
        }
    }

    for (i in 0..mapSize.y) { // for each map's column
        println(" ")
        for (j in 0..<mapSize.x) {
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