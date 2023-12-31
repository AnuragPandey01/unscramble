package com.example.unscramble.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.ui.components.FinalScoreDialog
import com.example.unscramble.ui.components.GameLayout
import com.example.unscramble.ui.components.HintButton
import com.example.unscramble.ui.components.ScoreCard

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel(),
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    if (gameUiState.isGameOver) {
        FinalScoreDialog(
            score = gameUiState.score,
            onPlayAgain = { gameViewModel.resetGame() }
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {

        if(!gameUiState.wordRevealed){
            HintButton(
                modifier = Modifier
                    .align(Alignment.End),
                text = "Reveal",
            ) {
                gameViewModel.onReveal()
            }
        }else{
            Text(
                modifier = Modifier
                .align(Alignment.End),
                text = "The word is ${gameViewModel.currentWord}",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        GameLayout(
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedWrong,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess()    },
            currentScrambledWord = gameUiState.currentScrambledWord,
            wordCount = gameUiState.currentWordCount,
            hintUsed = gameUiState.hintUsed,
            onHintUse = { gameViewModel.onHintUse() },
            hintPair = gameUiState.hint
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                gameViewModel.checkUserGuess()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                gameViewModel.skipWord()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Skip")
        }
        Spacer(modifier = Modifier.height(24.dp))
        ScoreCard(score = gameUiState.score)
    }
}


