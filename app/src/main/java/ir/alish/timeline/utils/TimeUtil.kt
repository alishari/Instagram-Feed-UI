package ir.alish.timeline.utils

import java.util.*

object TimeUtil {
    private const val A_SECOND = 1000
    private const val A_MINUTE = 60 * A_SECOND
    private const val AN_HOUR = 60 * A_MINUTE
    private const val A_DAY = 24 * AN_HOUR
    private const val A_YEAR: Long = 365 * A_DAY.toLong()
    fun format(time: Long) = (System.currentTimeMillis() - time).let {
        when (it) {
            in 0 until A_MINUTE -> (it / A_SECOND).toString() + " seconds ago"
            in A_MINUTE until 2 * A_MINUTE -> "1 minute ago"
            in 2 * A_MINUTE until AN_HOUR -> (it / A_MINUTE).toString() + " minutes ago"
            in AN_HOUR until 2 * AN_HOUR -> "1 hour ago"
            in 2 * AN_HOUR until A_DAY -> (it / AN_HOUR).toString() + " hours ago"
            in A_DAY until 2 * A_DAY -> "1 day ago"
            in 2 * A_DAY until 7 * A_DAY -> (it/ A_DAY).toString() + " day ago"
            in 7 * A_DAY until A_YEAR -> {
                val instance = Calendar.getInstance()
                instance.time = Date(time)
                val day = instance.get(Calendar.DAY_OF_MONTH)
                val month = instance.get(Calendar.MONTH)
                "${getMonthName(month)} $day"
            }
            else -> {
                val instance = Calendar.getInstance()
                instance.time = Date(time)
                val day = instance.get(Calendar.DAY_OF_MONTH)
                val month = instance.get(Calendar.MONTH)
                val year = instance.get(Calendar.YEAR)
                "${getMonthName(month)} $day, $year"
            }
        }
    }

    private fun getMonthName(month: Int): String {
        val monthNames = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        return monthNames[month]
    }
}