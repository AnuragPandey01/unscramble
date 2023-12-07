package com.example.unscramble.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unscramble.R

@Composable
fun ScoreCard(score:Int,modifier: Modifier = Modifier){

    Card(modifier = modifier){
        Text(
            text = stringResource(id = R.string.score,score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}