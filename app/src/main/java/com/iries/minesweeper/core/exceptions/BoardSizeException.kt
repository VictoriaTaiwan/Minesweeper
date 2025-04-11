package com.iries.minesweeper.core.exceptions

class BoardSizeException(
    message: String = "Map size can't be less than 2. Please, enter correct map size."
) : Exception()