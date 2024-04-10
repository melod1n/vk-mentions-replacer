package com.meloda.mentionsreplacer

fun main() {
    val input = readlnOrNull() ?: ""

    val regex = """\[(id|club)(\d+)\|([^]]+)]""".toRegex()

    val indexes = mutableListOf<MentionIndex>()

    val result = input.replace(regex) { result ->
        val group = requireNotNull(result.groups[2])
        val id = group.value.toIntOrNull() ?: -1
        val indexRange = group.range

        indexes += MentionIndex(
            id = id,
            indexRange = indexRange
        )

        result.groups[3]?.value ?: ""
    }

    println("Результат: $result")
}

private data class MentionIndex(
    val id: Int,
    val indexRange: IntRange
)
