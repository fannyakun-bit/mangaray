package tachiyomi.presentation.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val Typography.header: TextStyle
    @Composable
    // Webtoon-style: header section tebal dan gelap
    get() = titleMedium.copy(
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
    )
