package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Colors for the MangaRay default theme (electric violet, hot pink and mint accents).
 * Structure based on the Material 3 color scheme used by Mihon (Apache License 2.0).
 *
 * Key colors:
 * Primary #7C4DFF
 * Secondary #FF4F8B
 * Tertiary #2EE6C5
 * Neutral #0E0B16
 */
internal object TachiyomiColorScheme : BaseColorScheme() {

    override val darkScheme = darkColorScheme(
        primary = Color(0xFFB79CFF),
        onPrimary = Color(0xFF2A0F6B),
        primaryContainer = Color(0xFF5B34D6),
        onPrimaryContainer = Color(0xFFEADDFF),
        inversePrimary = Color(0xFF6B3FE0),
        secondary = Color(0xFFFF4F8B), // Unread badge
        onSecondary = Color(0xFF3A0018), // Unread badge text
        secondaryContainer = Color(0xFF3B2A66), // Navigation bar selector pill & progress indicator (remaining)
        onSecondaryContainer = Color(0xFFE6D9FF), // Navigation bar selector icon
        tertiary = Color(0xFF2EE6C5), // Downloaded badge
        onTertiary = Color(0xFF00382E), // Downloaded badge text
        tertiaryContainer = Color(0xFF005046),
        onTertiaryContainer = Color(0xFF9CF5E2),
        background = Color(0xFF0E0B16),
        onBackground = Color(0xFFE8E3F2),
        surface = Color(0xFF0E0B16),
        onSurface = Color(0xFFE8E3F2),
        surfaceVariant = Color(0xFF1A1530), // Navigation bar background (ThemePrefWidget)
        onSurfaceVariant = Color(0xFFCBC3DC),
        surfaceTint = Color(0xFFB79CFF),
        inverseSurface = Color(0xFFE8E3F2),
        inverseOnSurface = Color(0xFF2B2536),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        outline = Color(0xFF958DA8),
        outlineVariant = Color(0xFF473F5A),
        surfaceContainerLowest = Color(0xFF0A0710),
        surfaceContainerLow = Color(0xFF141022),
        surfaceContainer = Color(0xFF1A1530), // Navigation bar background
        surfaceContainerHigh = Color(0xFF221B3B),
        surfaceContainerHighest = Color(0xFF2B2347),
    )

    override val lightScheme = lightColorScheme(
        primary = Color(0xFF6B3FE0),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFE9DDFF),
        onPrimaryContainer = Color(0xFF22005D),
        inversePrimary = Color(0xFFCFBCFF),
        secondary = Color(0xFFD6336C), // Unread badge
        onSecondary = Color(0xFFFFFFFF), // Unread badge text
        secondaryContainer = Color(0xFFE7DDFB), // Navigation bar selector pill & progress indicator (remaining)
        onSecondaryContainer = Color(0xFF2A1A5C), // Navigation bar selector icon
        tertiary = Color(0xFF00796B), // Downloaded badge
        onTertiary = Color(0xFFFFFFFF), // Downloaded badge text
        tertiaryContainer = Color(0xFF9CF5E2),
        onTertiaryContainer = Color(0xFF00201B),
        background = Color(0xFFFDFBFF),
        onBackground = Color(0xFF1C1B20),
        surface = Color(0xFFFDFBFF),
        onSurface = Color(0xFF1C1B20),
        surfaceVariant = Color(0xFFF1ECF6), // Navigation bar background (ThemePrefWidget)
        onSurfaceVariant = Color(0xFF49454F),
        surfaceTint = Color(0xFF6B3FE0),
        inverseSurface = Color(0xFF312F36),
        inverseOnSurface = Color(0xFFF4EFF7),
        error = Color(0xFFBA1A1A),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        outline = Color(0xFF7A7585),
        outlineVariant = Color(0xFFCBC4D6),
        surfaceContainerLowest = Color(0xFFFFFFFF),
        surfaceContainerLow = Color(0xFFF7F2FB),
        surfaceContainer = Color(0xFFF1ECF6), // Navigation bar background
        surfaceContainerHigh = Color(0xFFEBE6F0),
        surfaceContainerHighest = Color(0xFFE5E0EB),
    )
}
