fun main(args: Array<String>) {
    var string = readLine() ?: ""

    val indexes = mutableListOf<Pair<Int, Int>>()
    val mentions = hashMapOf<String, String>()

    var startFrom = 0

    while (true) {
        val leftBracketIndex = string.indexOf('[', startFrom)
        val verticalLineIndex = string.indexOf('|', startFrom)
        val rightBracketIndex = string.indexOf(']', startFrom)

        if (leftBracketIndex == -1 ||
            verticalLineIndex == -1 ||
            rightBracketIndex == -1
        ) break

        val id = string.substring(leftBracketIndex + 1, verticalLineIndex)

        if (!id.matches(Regex("^id(\\d+)\$")) || rightBracketIndex - verticalLineIndex < 2) {
            break
        }

        val text = string.substring(verticalLineIndex + 1, rightBracketIndex)

        val str = "[$id|$text]"

        mentions[str] = text

        indexes += leftBracketIndex to leftBracketIndex + text.length

        startFrom = rightBracketIndex + 1
    }

    mentions.forEach {
        string = string.replace(it.key, it.value)
    }

    println()
    println("final string: $string")
}