package kotlinVersion

import java.io.File
import kotlin.random.Random

const val FILES_NUMBER = 19
const val LINES_NUMBER = 1000000
const val FILE_PATH = "C:/Users/aleks/IdeaProjects/IterativeParallelism/src/main/resources/kotlinVersion/"
const val FILE_NAME = "numbers"
const val FILE_TYPE = ".txt"

fun main() {
    generateFile(FILES_NUMBER, LINES_NUMBER)
}

fun generateFile(filesNumber: Int, linesNumber: Int) {
    for (i in 1..filesNumber) {
        File(FILE_PATH + FILE_NAME + i + FILE_TYPE).bufferedWriter().use { out ->
            out.write(generateNumbers(linesNumber))
        }
    }
}

fun generateNumbers(linesNumber: Int): String {
    val stringBuilder: StringBuilder = StringBuilder()
    for (i in 1..linesNumber) {
        stringBuilder.append(Random.nextInt(-10, 10)).append("\n")
    }
    return stringBuilder.toString()
}