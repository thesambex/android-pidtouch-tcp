package br.com.samuelives.printidtcp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkThem: (Boolean) = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(content = content, colorScheme = ColorScheme)
}