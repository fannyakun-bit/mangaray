package eu.kanade.tachiyomi.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.zacsweers.metrox.viewmodel.metroViewModel
import eu.kanade.presentation.components.AppBar
import eu.kanade.presentation.manga.components.MangaCover
import eu.kanade.presentation.util.Tab
import eu.kanade.tachiyomi.ui.manga.MangaScreen
import mihon.icons.materialsymbols.MaterialSymbols
import mihon.icons.materialsymbols.rounded.CalendarMonth
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.material.Scaffold
import tachiyomi.presentation.core.i18n.stringResource
import java.time.LocalDate

private val DAY_LABELS = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

data object DailyTab : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = stringResource(MR.strings.label_daily),
            icon = rememberVectorPainter(MaterialSymbols.Rounded.CalendarMonth),
        )

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = metroViewModel<DailyViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        var selectedPage by rememberSaveable { mutableIntStateOf(LocalDate.now().dayOfWeek.ordinal) }
        val labels = DAY_LABELS + stringResource(MR.strings.daily_completed)

        Scaffold(
            topBar = { scrollBehavior ->
                Column {
                    AppBar(
                        title = stringResource(MR.strings.label_daily),
                        scrollBehavior = scrollBehavior,
                    )
                    PrimaryScrollableTabRow(
                        selectedTabIndex = selectedPage,
                        edgePadding = 8.dp,
                        containerColor = MaterialTheme.colorScheme.surface,
                    ) {
                        labels.forEachIndexed { index, label ->
                            androidx.compose.material3.Tab(
                                selected = selectedPage == index,
                                onClick = { selectedPage = index },
                                text = {
                                    Text(
                                        text = label,
                                        fontWeight = if (selectedPage == index) FontWeight.Bold else FontWeight.Normal,
                                    )
                                },
                            )
                        }
                    }
                }
            },
        ) { contentPadding ->
            val items = state.pages.getOrElse(selectedPage) { emptyList() }
            if (items.isEmpty() && !state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(MR.strings.information_no_entries_found),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 110.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        end = 12.dp,
                        top = contentPadding.calculateTopPadding() + 12.dp,
                        bottom = contentPadding.calculateBottomPadding() + 12.dp,
                    ),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    items(items, key = { it.mangaId }) { item ->
                        Column(
                            modifier = Modifier.clickable { navigator.push(MangaScreen(item.mangaId)) },
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            MangaCover.Book(
                                data = item.cover,
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }
    }
}
