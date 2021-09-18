package ru.sber.datetime

import java.time.LocalDateTime

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val allZones: Set<String> = ZoneId.getAvailableZoneIds()
    val dt: LocalDateTime = LocalDateTime.now()

    val resultSet: MutableSet<String> = mutableSetOf()

    val zoneList: List<String> = ArrayList<String>(allZones)
    sort(zoneList)

    for (s in zoneList) {
        val zone: ZoneId = ZoneId.of(s)
        val zdt: ZonedDateTime = dt.atZone(zone)
        val offset: ZoneOffset = zdt.offset
        val secondsOfHours: Int = offset.totalSeconds % (60 * 60)

        if (secondsOfHours != 0) {
            resultSet.add(zone.toString())
        }
    }
    return resultSet
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastInMonthDayWeekList: MutableList<String> = mutableListOf()

    for (i in 1..12) {
        val yearMonth: YearMonth = YearMonth.of(year, i)
        val lastDay: LocalDate = yearMonth.atEndOfMonth()
        lastInMonthDayWeekList.add(lastDay.dayOfWeek.toString())
    }
    return lastInMonthDayWeekList
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var counter: Int = 0
    for (i in 1..12) {
        val yearMonth: YearMonth = YearMonth.of(year, i)
        val thirteenthDay: LocalDate = yearMonth.atDay(13)
        if (thirteenthDay.dayOfWeek == DayOfWeek.FRIDAY) {
            counter++
        }

    }

    return counter
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
}



