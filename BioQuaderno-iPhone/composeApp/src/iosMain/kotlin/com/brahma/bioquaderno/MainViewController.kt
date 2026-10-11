package com.brahma.bioquaderno

import androidx.compose.ui.window.ComposeUIViewController
import com.brahma.bioquaderno.ui.AppRoot

/** Punto di ingresso iOS: mostra la stessa AppRoot della versione Android. */
fun MainViewController() = ComposeUIViewController { AppRoot() }