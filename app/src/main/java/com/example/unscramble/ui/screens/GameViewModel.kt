package com.example.unscramble.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {


    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState>
        get() = _uiState.asStateFlow()
    var userGuess by mutableStateOf("")
        private set

    lateinit var currentWord: String
        private set

    private var usedWords: MutableSet<String> = mutableSetOf()

    init{
        resetGame()
    }


    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleWord(currentWord)
        }
    }

    private fun shuffleWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    fun checkUserGuess() {

        if (userGuess.equals(currentWord, ignoreCase = true)) {

            updateGameState(
                uiState.value.score
                    .plus(
                        if (uiState.value.wordRevealed) 0 else SCORE_INCREASE
                    )
            )

        } else {
            _uiState.update {
                it.copy(isGuessedWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    fun skipWord(){
        updateGameState(uiState.value.score)
    }

    private fun updateGameState(updatedScore: Int) {
        if(uiState.value.currentWordCount == MAX_NO_OF_WORDS){
            _uiState.update{
                it.copy(score = updatedScore, isGameOver = true, hintUsed = false, hint = Pair(0,' '))
            }
        }else{
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore,
                    currentWordCount = currentState.currentWordCount+1,
                    hintUsed = false,
                    hint = Pair(0,' '),
                    wordRevealed = false,
                )
            }
        }
    }


   fun onHintUse(){
       val len = currentWord.length-1
       val index = (0..len).random()
       val hint = currentWord[index]
       _uiState.update {
          it.copy(hintUsed = true, hint = Pair(index,hint))
       }
   }

    fun onReveal(){
        _uiState.update {
            it.copy(wordRevealed = true)
        }
    }

}