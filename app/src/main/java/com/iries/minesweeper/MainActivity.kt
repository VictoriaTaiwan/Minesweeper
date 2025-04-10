package com.iries.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.iries.minesweeper.core.Point
import com.iries.minesweeper.core.generateMap
import com.iries.minesweeper.ui.theme.MinesweeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinesweeperTheme {}
        }

        generateMap(Point(2, 1), Point(3, 2), 2)
    }
}