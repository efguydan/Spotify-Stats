package com.efedaniel.spotifystats.core.event

import androidx.compose.runtime.Immutable

@Immutable
data class Event<T: Any>(
    private val value: T
) {

    private var handled = false

    fun peek(): T = value

    fun get(): T? = if (handled) null else value.also { handled = true }
}
