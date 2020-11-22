package com.efedaniel.spotifystats.ui.commons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import timber.log.Timber

/**
 * Normally is expected to set only one of columns or itemWidth.
 * Currently, if both is set columns should get higher priority.
 * TODO come back to enforce this. Not both, or none. Only one.
 * TODO Consider adding a spacer for items
 *
 * Currently, preferredWidth takes priority
 */
@Composable
fun DynamicVerticalGrid(
    modifier: Modifier = Modifier,
    preferredColumns: Int? = null,
    preferredItemWidth: Dp? = null,
    content: @Composable () -> Unit
) {
    Layout(
        children = content,
        modifier = modifier
    ) { measurables, constraints ->
        // Use the closest item width that would use all space if a preferred width is given
        // Or use the number of columns to get width from total width available
        val itemWidth = preferredItemWidth?.value?.toInt()?.let { constraints.maxWidth / (constraints.maxWidth / it) }
            ?: preferredColumns?.let { constraints.maxWidth / it }
            ?: throw Exception("No valid value for preferredItemWidth and preferredColumns")

        Timber.d(itemWidth.toString())
        Timber.d(constraints.toString())

        val columns = constraints.maxWidth / itemWidth

        // Keep given height constraints, but set an exact width
        val itemConstraints = constraints.copy(
            minWidth = itemWidth,
            maxWidth = itemWidth
        )

        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(itemConstraints) }

        // Track each column height so we can calculate the overall height
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight).coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // Track the Y co-ord per column we have placed up to
            val columnY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.place(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
                Timber.d("Placeable Width is %s", placeable.width.toString())
                Timber.d("Placeable Height is %s", placeable.height.toString())
            }
        }
    }
}
