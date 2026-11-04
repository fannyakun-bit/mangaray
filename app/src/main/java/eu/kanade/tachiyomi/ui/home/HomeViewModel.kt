package eu.kanade.tachiyomi.ui.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import tachiyomi.domain.history.interactor.GetHistory
import tachiyomi.domain.manga.interactor.GetLibraryManga
import tachiyomi.domain.manga.model.MangaCover
import tachiyomi.domain.manga.model.asMangaCover
import tachiyomi.domain.updates.interactor.GetUpdates
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds

@Immutable
data class HomeItem(
    val mangaId: Long,
    val title: String,
    val subtitle: String?,
    val cover: MangaCover,
)

@Immutable
data class HomeState(
    val isLoading: Boolean = true,
    val banner: List<HomeItem> = emptyList(),
    val continueReading: List<HomeItem> = emptyList(),
    val newUpdates: List<HomeItem> = emptyList(),
    val favorites: List<HomeItem> = emptyList(),
) {
    val isEmpty: Boolean
        get() = banner.isEmpty() && continueReading.isEmpty() && newUpdates.isEmpty() && favorites.isEmpty()
}

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class, binding = binding<ViewModel>())
class HomeViewModel(
    private val getHistory: GetHistory,
    private val getUpdates: GetUpdates,
    private val getLibraryManga: GetLibraryManga,
) : ViewModel() {

    private val history: Flow<List<HomeItem>> = getHistory.subscribe("")
        .map { list ->
            list.distinctBy { it.mangaId }.take(MAX_ITEMS).map {
                HomeItem(
                    mangaId = it.mangaId,
                    title = it.title,
                    subtitle = if (it.chapterNumber >= 0) "Ch. ${formatNumber(it.chapterNumber)}" else null,
                    cover = it.coverData,
                )
            }
        }
        .catch { emit(emptyList()) }

    private val updates: Flow<List<HomeItem>> = getUpdates.subscribe(
        Clock.System.now().minus(1, DateTimeUnit.MONTH, TimeZone.currentSystemDefault()),
        unread = null,
        started = null,
        bookmarked = null,
        hideExcludedScanlators = false,
        includedCategories = emptyList(),
        excludedCategories = emptyList(),
    )
        .map { list ->
            list.distinctBy { it.mangaId }.take(MAX_ITEMS).map {
                HomeItem(
                    mangaId = it.mangaId,
                    title = it.mangaTitle,
                    subtitle = it.chapterName,
                    cover = it.coverData,
                )
            }
        }
        .catch { emit(emptyList()) }

    private val library: Flow<List<tachiyomi.domain.library.model.LibraryManga>> = getLibraryManga.subscribe()

    val state: StateFlow<HomeState> = combine(history, updates, library) { history, updates, library ->
        val favorites = library
            .sortedByDescending { it.lastRead }
            .take(MAX_ITEMS)
            .map { it.toHomeItem() }
        val banner = library
            .filter { it.latestUpload != null }
            .sortedByDescending { it.latestUpload }
            .take(BANNER_ITEMS)
            .map { it.toHomeItem() }
            .ifEmpty { history.take(BANNER_ITEMS) }
        HomeState(
            isLoading = false,
            banner = banner,
            continueReading = history,
            newUpdates = updates,
            favorites = favorites,
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds), HomeState())

    private fun tachiyomi.domain.library.model.LibraryManga.toHomeItem() = HomeItem(
        mangaId = manga.id,
        title = manga.title,
        subtitle = null,
        cover = manga.asMangaCover(),
    )

    private fun formatNumber(value: Double): String {
        return if (value % 1.0 == 0.0) value.toLong().toString() else value.toString()
    }

    private companion object {
        const val MAX_ITEMS = 20
        const val BANNER_ITEMS = 5
    }
}
