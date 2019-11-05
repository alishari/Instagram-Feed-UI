package ir.alish.timeline.utils

object NumberUtil {
    fun format(num: Long) = when (num) {
        in 0 until 1000 -> num.toString()
        in 1000 until 1000000 -> (num / 1000).toString() + "," + (num % 1000).toString()
        in 1000000 until 1000000000 -> (num / 1000000).toString() + "," + ((num % 1000000) / 1000).toString() + "," + (num % 1000).toString()
        else -> num.toString()
    }
}
