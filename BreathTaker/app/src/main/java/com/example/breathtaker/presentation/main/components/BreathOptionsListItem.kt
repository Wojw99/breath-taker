package com.example.breathtaker.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.breathtaker.R
import com.example.breathtaker.common.IconTagMapper
import com.example.breathtaker.domain.model.ArticleLimited
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.ui.theme.Colors

@Composable
fun BreathOptionsListItem(
    text: String,
    subText: String,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = CommonValues.screenPadding / 2)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(CommonValues.roundedCornerSize))
            .background(color = Colors.MainColor)
            .clickable { onItemClick() }
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = CommonValues.screenPadding, vertical = CommonValues.screenPadding / 2)
        ){
            Text(
                text = text,
                fontSize = CommonValues.buttonTextFontSize,
                color = Colors.LightColor
            )
            Text(
                text = subText,
                fontSize = CommonValues.buttonSubtextFontSize,
                color = Colors.LightColor50
            )
        }
    }
}