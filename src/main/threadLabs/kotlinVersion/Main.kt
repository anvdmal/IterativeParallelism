package kotlinVersion

import java.io.File
import kotlinx.coroutines.*
import java.io.IOException

private const val COUNT = 19
private var sumMany: Long = 0

fun main() = runBlocking {
    oneCoroutine()
    manyCoroutines()
}

fun oneCoroutine() {
    var time: Long = System.currentTimeMillis()
    val sum: Long = sumNumbers(0, FILES_NUMBER)

    time = System.currentTimeMillis() - time
    println("Sum = $sum \nOne coroutine worked $time ms and exited\n")

}

fun sumNumbers(startFrom: Int, endTo: Int): Long {
    var partOfSum: Long = 0
    for (j in startFrom + 1..endTo) {
        try {
            val file = File(FILE_PATH + FILE_NAME + j + FILE_TYPE)
            file.bufferedReader().forEachLine {
                partOfSum += it.toInt()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return partOfSum
}

suspend fun manyCoroutines() = coroutineScope {
    var time: Long = System.currentTimeMillis()

    val numberOfFiles: Int = FILES_NUMBER / COUNT
    val remainNumberOfFiles: Int = FILES_NUMBER % COUNT

    val coroutines = List(COUNT) { i ->
            async(Dispatchers.Default) {
        val startFrom: Int = numberOfFiles * i
        var endTo: Int = numberOfFiles * (i + 1)

        if (i == COUNT - 1) {
            endTo += remainNumberOfFiles
        }

        val partSum = sumNumbers(startFrom, endTo)

        sumMany += partSum
    }
    }
    coroutines.awaitAll()

    time = System.currentTimeMillis() - time
    println("Sum = $sumMany \n$COUNT coroutines worked $time ms and exited\n")
}