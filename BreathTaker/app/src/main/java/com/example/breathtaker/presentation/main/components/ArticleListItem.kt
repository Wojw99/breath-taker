package com.example.breathtaker.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.breathtaker.R
import com.example.breathtaker.common.IconTagMapper
import com.example.breathtaker.domain.model.ArticleLimited
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.ui.theme.Colors

@Composable
fun ArticleListItem(
    articleLimited: ArticleLimited,
    onItemClick: (ArticleLimited) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = CommonValues.screenPadding / 2)
            .clip(shape = RoundedCornerShape(CommonValues.roundedCornerSize))
            .background(color = Colors.MainColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CommonValues.screenPadding, vertical = CommonValues.screenPadding / 2)
                .clickable { onItemClick(articleLimited) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val iconId = IconTagMapper.getDrawableIdFor(articleLimited.iconTag)
            val painter = painterResource(id = iconId)
            Icon(
                painter = painter,
                contentDescription = stringResource(id = R.string.icon),
                tint = Colors.LightColor,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = articleLimited.title,
                    fontSize = CommonValues.buttonTextFontSize,
                    color = Colors.LightColor
                )
                Text(
                    text = articleLimited.textRead,
                    fontSize = CommonValues.buttonSubtextFontSize,
                    color = Colors.LightColor50
                )
            }
        }
    }
}