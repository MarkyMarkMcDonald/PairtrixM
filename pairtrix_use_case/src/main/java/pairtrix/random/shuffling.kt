package pairtrix.random

import java.util.*

fun <E> List<E>.shuffle(): List<E> {
    val randomizedList = ArrayList(this)
    Collections.shuffle(randomizedList)
    return randomizedList
}

