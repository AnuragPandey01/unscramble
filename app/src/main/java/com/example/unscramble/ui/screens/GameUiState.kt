package com.example.unscramble.ui.screens

data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWrong:Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val isGameOver: Boolean =false,
    val hintUsed: Boolean = false,
    val hint:Pair<Int,Char> = Pair(0,' '),
    val wordRevealed:Boolean = false
)