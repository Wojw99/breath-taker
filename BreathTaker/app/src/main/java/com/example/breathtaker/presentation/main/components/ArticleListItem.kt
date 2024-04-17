package com.example.breathtaker.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.breathtaker.domain.model.ArticleLimited

@Composable
fun ArticleListItem(
    articleLimited: ArticleLimited,
    onItemClick: (ArticleLimited) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(articleLimited) }
            .padding(20.dp)
    ){
        Text(
            text = "${articleLimited.title} ${articleLimited.iconName}",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = if(articleLimited.textRead == String()) "hello" else "world",
        )
    }
}