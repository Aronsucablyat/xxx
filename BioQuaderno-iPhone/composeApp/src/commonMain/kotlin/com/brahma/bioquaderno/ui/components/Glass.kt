package com.brahma.bioquaderno.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.brahma.bioquaderno.ui.theme.LocalAppSettings
import com.brahma.bioquaderno.ui.theme.Palette

/**
 * Sfondo "aurora" animato: tre blob di colore che si muovono e cambiano tinta,
 * come un liquido colorato dietro alle superfici in vetro.
 */
@Composable
fun AuroraBackground(
    palette: Palette,
    dark: Boolean,
    modifier: Modifier = Modifier
) {
    val settings = LocalAppSettings.current
    val scheme = MaterialTheme.colorScheme

    // Stile di sfondo scelto dall'utente.
    when (settings.backgroundStyle) {
        "nessuno", "tinta" -> {
            Box(modifier.fillMaxSize().background(scheme.background))
            return
        }
        "gradiente" -> {
            val c0 = palette.aurora.getOrElse(0) { palette.seed }
            val c2 = palette.aurora.getOrElse(2) { palette.seed }
            val top = lerp(scheme.background, c0, if (dark) 0.18f else 0.12f)
            val bot = lerp(scheme.background, c2, if (dark) 0.10f else 0.06f)
            Box(
                modifier.fillMaxSize().background(
                    Brush.verticalGradient(listOf(top, scheme.background, bot))
                )
            )
            return
        }
    }

    val anim = settings.animations
    val intensity = when (settings.auroraIntensity) {
        "tenue" -> 0.6f
        "vivace" -> 1.6f
        else -> 1f
    }

    val a0 = palette.aurora.getOrElse(0) { palette.seed }
    val a1 = palette.aurora.getOrElse(1) { palette.seed }
    val a2 = palette.aurora.getOrElse(2) { palette.seed }

    val c1: Color
    val c2: Color
    val c3: Color
    val p1: Float
    val p2: Float
    val p3: Float

    if (anim) {
        val t = rememberInfiniteTransition(label = "aurora")
        val f1 by t.animateFloat(0f, 1f, infiniteRepeatable(tween(9000, easing = LinearEasing), RepeatMode.Reverse), label = "f1")
        val f2 by t.animateFloat(0f, 1f, infiniteRepeatable(tween(11000, easing = LinearEasing), RepeatMode.Reverse), label = "f2")
        val f3 by t.animateFloat(0f, 1f, infiniteRepeatable(tween(13000, easing = LinearEasing), RepeatMode.Reverse), label = "f3")
        c1 = lerp(a0, a1, f1)
        c2 = lerp(a1, a2, f2)
        c3 = lerp(a2, a0, f3)
        p1 = t.animateFloat(0f, 1f, infiniteRepeatable(tween(12000, easing = LinearEasing), RepeatMode.Reverse), label = "p1").value
        p2 = t.animateFloat(0f, 1f, infiniteRepeatable(tween(16000, easing = LinearEasing), RepeatMode.Reverse), label = "p2").value
        p3 = t.animateFloat(0f, 1f, infiniteRepeatable(tween(19000, easing = LinearEasing), RepeatMode.Reverse), label = "p3").value
    } else {
        c1 = a0
        c2 = a1
        c3 = a2
        p1 = 0f
        p2 = 0.25f
        p3 = 0.6f
    }

    Canvas(modifier.fillMaxSize()) {
        val minDim = minOf(size.width, size.height)
        val alpha = ((if (dark) 0.42f else 0.62f) * intensity).coerceIn(0f, 1f)
        fun blob(color: Color, cx: Float, cy: Float, r: Float) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(color.copy(alpha = alpha), color.copy(alpha = 0f)),
                    center = Offset(cx, cy),
                    radius = r
                ),
                radius = r,
                center = Offset(cx, cy)
            )
        }
        blob(c1, size.width * (0.15f + 0.28f * p1), size.height * (0.18f + 0.14f * p2), minDim * 0.66f)
        blob(c2, size.width * (0.84f - 0.20f * p2), size.height * (0.24f + 0.18f * p1), minDim * 0.60f)
        blob(c3, size.width * (0.50f + 0.25f * p3), size.height * 0.88f, minDim * 0.72f)
    }
}

/**
 * Una "scheda di vetro liquido": superficie traslucida, bordo luminoso e
 * riflesso superiore. Se l'effetto glass è disattivato, diventa una normale superficie.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    containerColor: Color? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    val dark = scheme.background.luminance() < 0.5f
    val settings = LocalAppSettings.current

    // Angoli delle schede: seguono lo stile scelto
    val corner = when (settings.cornerStyle) {
        "quadrato" -> 8.dp
        "morbido" -> 26.dp
        "pillola" -> 34.dp
        else -> 20.dp
    }
    val effShape = shape ?: RoundedCornerShape(corner)
    // Spazio interno: "compatta / normale / ampia" + modalità compatta
    val base16 = PaddingValues(16.dp)
    val effPadding = when {
        contentPadding != base16 -> contentPadding
        settings.denseMode -> PaddingValues(10.dp)
        settings.cardPadding == "compatta" -> PaddingValues(12.dp)
        settings.cardPadding == "ampia" -> PaddingValues(22.dp)
        else -> base16
    }
    // Spessore del bordo scelto
    val borderW = when (settings.cardBorder) {
        "nessuno" -> 0.dp
        "spesso" -> 2.dp
        else -> 1.dp
    }
    // Ombra: piatta / morbida / forte
    val shadowDp = when (settings.cardElevation) {
        "piatta" -> 0.dp
        "forte" -> if (settings.glass) 18.dp else 10.dp
        else -> if (settings.glass) 8.dp else 2.dp
    }

    val base = containerColor ?: if (dark) scheme.surface else Color.White

    // Trasparenza del vetro: impostabile ("riduci trasparenza" = piu' opaco)
    val topAlpha = if (settings.glass) (if (settings.reduceTransparency) 0.96f else 0.72f) else 1f
    val botAlpha = if (settings.glass) (if (settings.reduceTransparency) 0.90f else 0.40f) else 1f

    val fill: Brush = if (settings.glass) {
        Brush.verticalGradient(
            listOf(base.copy(alpha = topAlpha), base.copy(alpha = botAlpha))
        )
    } else {
        Brush.verticalGradient(listOf(base.copy(alpha = 1f), base.copy(alpha = 1f)))
    }

    val borderBrush: Brush = if (settings.glass) {
        if (!dark) Brush.linearGradient(
            listOf(Color.White.copy(alpha = 0.95f), Color.White.copy(alpha = 0.15f), Color.White.copy(alpha = 0.45f))
        )
        else Brush.linearGradient(
            listOf(Color.White.copy(alpha = 0.35f), Color.White.copy(alpha = 0.05f), Color.White.copy(alpha = 0.15f))
        )
    } else {
        Brush.linearGradient(listOf(scheme.outlineVariant, scheme.outlineVariant))
    }

    Box(
        modifier
            .shadow(
                elevation = shadowDp,
                shape = effShape,
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.18f),
                spotColor = Color.Black.copy(alpha = 0.22f)
            )
            .clip(effShape)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .background(fill, effShape)
    ) {
        if (settings.glass) {
            // riflesso superiore (sheen)
            Box(
                Modifier
                    .matchParentSize()
                    .drawBehind {
                        drawRect(
                            brush = Brush.verticalGradient(
                                0f to Color.White.copy(alpha = if (dark) 0.10f else 0.30f),
                                0.55f to Color.White.copy(alpha = 0f)
                            )
                        )
                    }
            )
        }
        // bordo (spessore in base all'impostazione "bordo schede")
        if (borderW > 0.dp) {
            Box(Modifier.matchParentSize().border(borderW, borderBrush, effShape))
        }
        Column(Modifier.padding(effPadding), content = content)
    }
}