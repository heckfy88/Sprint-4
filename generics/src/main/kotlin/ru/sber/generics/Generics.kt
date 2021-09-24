package ru.sber.generics


// 1.
fun <K,V> compare(p1: Pair<K,V>, p2: Pair<K,V>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}


// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
        var counter: Int = 0

        for(arrEl in anArray) {
            if (arrEl > elem) {
                counter++
            }
        }

    return counter
}


// 3.
class  Sorter<T: Comparable<T>> {

    private val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {

    private val stack: MutableList<T> = mutableListOf()

    fun push(value: T) = stack.add(value)

    fun pop(): T = stack.removeLast()

    fun isEmpty(): Boolean = stack.isEmpty()

    fun size(): Number = stack.size

    fun peek(): T = stack.last()

}