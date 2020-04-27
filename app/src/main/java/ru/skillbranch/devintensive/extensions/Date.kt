package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var diff: Long = date.time - this.time
    val future: Boolean = diff < 0
    if (future) diff = -diff
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    return if (seconds == 0L || seconds == 1L) "только что"
        else if (seconds in 2..45) Utils.specifyTime(future, "несколько секунд")
        else if (seconds in 46..75) Utils.specifyTime(future, "минуту")
        else if (seconds > 75 && minutes < 45) Utils.specifyTime(future, TimeUnits.MINUTE.plural(minutes.toInt()))
        else if (minutes in 46..75) Utils.specifyTime(future, "час")
        else if (minutes > 75 && hours < 22) Utils.specifyTime(future, TimeUnits.HOUR.plural(hours.toInt()))
        else if (hours in 22..26) Utils.specifyTime(future, "день")
        else if (hours > 26 && days < 360) Utils.specifyTime(future, TimeUnits.DAY.plural(days.toInt()))
        else if (days > 360) "${if (future) "более чем через год" else "более года назад"}"
        else throw IllegalArgumentException("incorrect input")
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String = Utils.makePlural(value, "секунду", "секунды", "секунд")
    },
    MINUTE {
        override fun plural(value: Int): String = Utils.makePlural(value, "минуту", "минуты", "минут")
    },
    HOUR {
        override fun plural(value: Int): String = Utils.makePlural(value, "час", "часа", "часов")
    },
    DAY {
        override fun plural(value: Int): String = Utils.makePlural(value, "день", "дня", "дней")
    };

    abstract fun plural(value: Int): String
}
