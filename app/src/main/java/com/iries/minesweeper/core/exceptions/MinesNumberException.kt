package com.iries.minesweeper.core.exceptions

class MinesNumberException(
    message: String = "Mines number can't be below 1, bigger than board size or equals to it. " +
            "Please, enter correct amount of mines."
) : Exception()