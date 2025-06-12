package org.example.project.whether.domain.models

fun <R> safeCall(defaultValue:R,block: () -> R): R{
    return runCatching(block).getOrNull()?:defaultValue
}