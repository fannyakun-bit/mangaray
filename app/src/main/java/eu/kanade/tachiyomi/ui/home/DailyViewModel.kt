package eu.kanade.tachiyomi.ui.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import eu.kanade.tachiyomi.source.model.SManga
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import tachiyomi.domain.manga.interactor.GetLibraryManga
import tachiyomi.domain.manga.model.asMangaCover
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration.Companion.seconds

@Immutable
data class DailyState(
    val isLoading: Boolean = true,
    /** Index 0..6 = MON..SUN, index 7 = Completed. */
    val pages: List<List<HomeItem>> = List(PAGE_COUNT) { emptyList() },
) {
    companion object {
        const val PAGE_COUNT = 8
        const val COMPLETED_PAGE = 7
    }
}

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class, binding = binding<ViewModel>())
class DailyViewModel(
    getLibraryManga: GetLibraryManga,
) : ViewModel() {

    val state: StateFlow<DailyState> = getLibraryManga.subscribe()
        .map { library ->
            val pages = List(DailyState.PAGE_COUNT) { mutableListOf<HomeItem>() }
            library
                .sortedByDescending { it.latestUpload ?: it.chapterFetchedAt }
                .forEach { item ->
                    val home = HomeItem(
                        mangaId = item.manga.id,
                        title = item.manga.title,
                        subtitle = null,
                        cover = item.manga.asMangaCover(),
                    )
                    if (item.manga.status == SManga.COMPLETED.toLong()) {
                        pages[DailyState.COMPLETED_PAGE].add(home)
                        return@forEach
                    }
                    // Day of the last chapter update; entries without any date are skipped
                    val date = item.latestUpload ?: item.chapterFetchedAt ?: item.manga.lastUpdate
                    if (date != null) {
                        val day = Instant.ofEpochMilli(date.toEpochMilliseconds())
                            .atZone(ZoneId.systemDefault())
                            .dayOfWeek
                            .ordinal
                        pages[day].add(home)
                    }
                }
            DailyState(isLoading = false, pages = pages)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds), DailyState())
}
