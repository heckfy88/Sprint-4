package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int {
        return { valueToPow -> valueToPow.toDouble().pow(pow).toInt()}
    }
}


fun main() {
    print(PowFactory.buildPowFunction(2)(100))
}