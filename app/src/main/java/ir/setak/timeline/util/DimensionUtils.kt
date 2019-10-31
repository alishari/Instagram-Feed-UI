package ir.setak.timeline.util

import android.content.Context
import android.util.TypedValue


object DimensionUtils {
    fun toDp(context: Context, input: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        input,
        context.resources.displayMetrics
    )
}
