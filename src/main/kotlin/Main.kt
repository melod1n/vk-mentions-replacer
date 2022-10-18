fun main(args: Array<String>) {
    var newMessageText = readLine() ?: ""

    val idsIndexes = mutableListOf<Triple<Int, Int, Int>>()
    val mentions = hashMapOf<String, String>()

    var startFrom = 0

    while (true) {
        val leftBracketIndex = newMessageText.indexOf('[', startFrom)
        val verticalLineIndex = newMessageText.indexOf('|', startFrom)
        val rightBracketIndex = newMessageText.indexOf(']', startFrom)

        if (leftBracketIndex == -1 ||
            verticalLineIndex == -1 ||
            rightBracketIndex == -1
        ) break

        val idPart = newMessageText.substring(leftBracketIndex + 1, verticalLineIndex)

        val actualId = idPart.substring(2, idPart.length).toIntOrNull() ?: -1

        if (!idPart.matches(Regex("^id(\\d+)\$")) || rightBracketIndex - verticalLineIndex < 2) {
            break
        }

        val text = newMessageText.substring(verticalLineIndex + 1, rightBracketIndex)

        val str = "[$idPart|$text]"

        mentions[str] = text

        idsIndexes += Triple(actualId, leftBracketIndex, leftBracketIndex + text.length)

        startFrom = rightBracketIndex + 1
    }

    mentions.forEach {
        newMessageText = newMessageText.replace(it.key, it.value)
    }

    println()
    println("final string: $newMessageText")
}