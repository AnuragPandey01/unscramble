package com.example.unscramble.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unscramble.R

@Composable
fun HintButton(modifier: Modifier = Modifier,onClick:()->Unit) {

    OutlinedButton(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_hint), contentDescription = "hint")
            Text(text = "Hint")
        }
    }
}

@Preview
@Composable
fun HintButtonPreview(){
    HintButton(onClick = {})
}