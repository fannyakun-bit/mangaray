package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Default MangaRay theme, Webtoon-inspired: clean white/near-black surfaces with a
 * signature green accent and a red badge for new/unread content.
 * Structure based on the Material 3 color scheme used by Mihon (Apache License 2.0).
 *
 * Key colors:
 * Primary #00C95E (green)
 * Secondary #FF4B4B (new/unread badge)
 * Tertiary #2F80ED (downloaded badge)
 */
internal object TachiyomiColorScheme : BaseColorScheme() {

    override val darkScheme = darkColorScheme(
        primary = Color(0xFF00DC64),
        onPrimary = Color(0xFF00210D),
        primaryContainer = Color(0xFF00A650),
        onPrimaryContainer = Color(0xFFFFFFFF),
        inversePrimary = Color(0xFF00A650),
        secondary = Color(0xFFFF5C5C), // Unread badge
        onSecondary = Color(0xFFFFFFFF), // Unread badge text
        secondaryContainer = Color(0xFF16301F), // Navigation bar selector & progress indicator (remaining)
        onSecondaryContainer = Color(0xFF00DC64),
        tertiary = Color(0xFF5B9DFF), // Downloaded badge
        onTertiary = Color(0xFF001A3D), // Downloaded badge text
        tertiaryContainer = Color(0xFF1A3558),
        onTertiaryContainer = Color(0xFFD3E4FF),
        background = Color(0xFF121212),
        onBackground = Color(0xFFF2F2F2),
        surface = Color(0xFF121212),
        onSurface = Color(0xFFF2F2F2),
        surfaceVariant = Color(0xFF1E1E1E), // Navigation bar background (ThemePrefWidget)
        onSurfaceVariant = Color(0xFFA8A8A8),
        surfaceTint = Color(0xFF00DC64),
        inverseSurface = Color(0xFFF2F2F2),
        inverseOnSurface = Color(0xFF202020),
        error = Color(0xFFFF6B6B),
        onError = Color(0xFF3A0000),
        errorContainer = Color(0xFF5C1111),
        onErrorContainer = Color(0xFFFFDAD6),
        outline = Color(0xFF6E6E6E),
        outlineVariant = Color(0xFF2E2E2E),
        surfaceContainerLowest = Color(0xFF0D0D0D),
        surfaceContainerLow = Color(0xFF171717),
        surfaceContainer = Color(0xFF1B1B1B), // Navigation bar background
        surfaceContainerHigh = Color(0xFF242424),
        surfaceContainerHighest = Color(0xFF2D2D2D),
    )

    override val lightScheme = lightColorScheme(
        primary = Color(0xFF00C95E),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFD9F9E5),
        onPrimaryContainer = Color(0xFF00351A),
        inversePrimary = Color(0xFF00DC64),
        secondary = Color(0xFFFF4B4B), // Unread badge
        onSecondary = Color(0xFFFFFFFF), // Unread badge text
        secondaryContainer = Color(0xFFE6F8ED), // Navigation bar selector & progress indicator (remaining)
        onSecondaryContainer = Color(0xFF00A650),
        tertiary = Color(0xFF2F80ED), // Downloaded badge
        onTertiary = Color(0xFFFFFFFF), // Downloaded badge text
        tertiaryContainer = Color(0xFFD7E7FF),
        onTertiaryContainer = Color(0xFF001B3E),
        background = Color(0xFFFFFFFF),
        onBackground = Color(0xFF111111),
        surface = Color(0xFFFFFFFF),
        onSurface = Color(0xFF111111),
        surfaceVariant = Color(0xFFF4F4F4), // Navigation bar background (ThemePrefWidget)
        onSurfaceVariant = Color(0xFF6B6B6B),
        surfaceTint = Color(0xFF00C95E),
        inverseSurface = Color(0xFF2A2A2A),
        inverseOnSurface = Color(0xFFF5F5F5),
        error = Color(0xFFD93025),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        outline = Color(0xFF9A9A9A),
        outlineVariant = Color(0xFFE6E6E6),
        surfaceContainerLowest = Color(0xFFFFFFFF),
        surfaceContainerLow = Color(0xFFFAFAFA),
        surfaceContainer = Color(0xFFF5F5F5), // Navigation bar background
        surfaceContainerHigh = Color(0xFFEFEFEF),
        surfaceContainerHighest = Color(0xFFE9E9E9),
    )
}
