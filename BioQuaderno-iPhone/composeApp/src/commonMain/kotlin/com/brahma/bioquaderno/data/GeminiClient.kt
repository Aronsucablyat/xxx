package com.brahma.bioquaderno.data

import com.brahma.bioquaderno.platform.Platform
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

/**
 * Chiamata alla Gemini API di Google (REST).
 * Sulle due piattaforme usa il client HTTP di sistema:
 * HttpURLConnection su Android, NSURLSession su iPhone.
 * Se il modello scelto non esiste, prova in automatico con modelli più vecchi.
 */
object GeminiClient {

    private val fallbackModels = listOf("gemini-2.5-flash", "gemini-2.0-flash", "gemini-1.5-flash")

    suspend fun ask(
        apiKey: String,
        model: String,
        system: String,
        question: String
    ): Result<String> {
        val chosen = model.trim().ifBlank { "gemini-2.5-flash" }
        val candidates = if (chosen in fallbackModels) fallbackModels
        else listOf(chosen) + fallbackModels.filter { it != chosen }

        var lastError: String? = null

        for (m in candidates) {
            try {
                val url = "https://generativelanguage.googleapis.com/v1beta/models/$m:generateContent?key=$apiKey"
                val body = buildJsonObject {
                    put(
                        "system_instruction",
                        buildJsonObject { put("parts", buildJsonArray { add(buildJsonObject { put("text", system) }) }) }
                    )
                    put(
                        "contents",
                        buildJsonArray {
                            add(
                                buildJsonObject {
                                    put("role", "user")
                                    put("parts", buildJsonArray { add(buildJsonObject { put("text", question) }) })
                                }
                            )
                        }
                    )
                }.toString()

                val res = Platform.httpPostJson(
                    url = url,
                    headers = mapOf("Content-Type" to "application/json"),
                    body = body
                )

                if (res.code in 200..299) {
                    val text = runCatching { parseResponse(res.body) }.getOrDefault("")
                    if (text.isNotBlank()) return Result.success(text.trim())
                    lastError = "Risposta vuota dal modello $m"
                } else {
                    val err = parseError(res.body, res.code)
                    lastError = err
                    if (res.code == 404 || (res.code == 400 && err.contains("model", ignoreCase = true))) {
                        continue // modello non trovato: prova il successivo
                    }
                    return Result.failure(IllegalStateException(err))
                }
            } catch (e: Exception) {
                lastError = e.message ?: "Errore di rete sconosciuto"
            }
        }
        return Result.failure(IllegalStateException(lastError ?: "Errore sconosciuto"))
    }

    private fun parseResponse(body: String): String {
        val root = Json.parseToJsonElement(body).jsonObject
        return root["candidates"]?.jsonArray?.firstOrNull()
            ?.jsonObject?.get("content")?.jsonObject
            ?.get("parts")?.jsonArray?.firstOrNull()
            ?.jsonObject?.get("text")?.jsonPrimitive?.contentOrNull
            ?: ""
    }

    private fun parseError(body: String, code: Int): String =
        try {
            Json.parseToJsonElement(body).jsonObject["error"]?.jsonObject
                ?.get("message")?.jsonPrimitive?.contentOrNull ?: "Errore $code"
        } catch (e: Exception) {
            "Errore $code"
        }
}