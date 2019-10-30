package ir.setak.timeline.ui.adapter


import android.graphics.Color
import android.os.Build
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.R
import ir.setak.timeline.model.Item
import ir.setak.timeline.ui.fragment.PostFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.post_content.view.*
import kotlinx.android.synthetic.main.post_header.view.*


/**
 * [RecyclerView.Adapter] that can display a [Item] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */

const val DEFAULT_LINES = 2
const val MAX_LINES = 20

class PostAdapter(
    private val mValues: List<Item>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PostAdapter.PostVH>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener {
            val item = it.tag as Item
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return PostVH(view)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        val senderName = "Arezo.Nazeri"
        val captionText =
            StringBuilder().append("Hi, This is my fist post in timeline, I hope you like it! ")
                .append("Hi, This is my fist post in timeline, I hope you like it! ")
                .append("Hi, This is my fist post in timeline, I hope you like it! ")
                .append("Hi, This is my fist post in timeline, I hope you like it! ").toString()

        holder.captionText.post {
            holder.setCaption(senderName, captionText)
            holder.captionText.setOnClickListener {
                holder.captionText.let {
                    it.maxLines = MAX_LINES
                    it.text = getFullCaption(senderName, captionText)
                }
            }
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class PostVH(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.sender_name
        val mContentView: TextView = mView.content
        val captionText: TextView = mView.caption_text

        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                captionText.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    fun PostVH.setCaption(senderName: String, caption: String) {
        val context = captionText.context
        captionText.text = getFullCaption(senderName, caption)

        if (captionText.lineCount > DEFAULT_LINES) {
            val lastCharShown = captionText.layout.getLineVisibleEnd(DEFAULT_LINES - 1)
            captionText.maxLines = DEFAULT_LINES
            val moreString = context.getString(R.string.read_more)
            val suffix = "  $moreString"
            // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
            val actionDisplayText = context.getString(R.string.more_dots) + suffix

            captionText.text = SpannableStringBuilder()
                .bold { append(senderName) }
                .append("  ")
                .append(
                    caption.substring(
                        0,
                        lastCharShown - suffix.length - 3 - moreString.length - senderName.length
                    )
                )
                .color(Color.BLUE) { append(actionDisplayText) }


//            val truncatedSpannableString = SpannableString(actionDisplayText)
//            val startIndex = actionDisplayText.indexOf(moreString)
//            truncatedSpannableString.setSpan(
//                ForegroundColorSpan(
//                    ContextCompat.getColor(
//                        context,
//                        android.R.color.holo_blue_light
//                    )
//                ),
//                startIndex,
//                startIndex + moreString.length,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//            )
//            captionText.text = truncatedSpannableString
        }
    }

    private fun getFullCaption(
        senderName: String,
        caption: String
    ): SpannableStringBuilder {
        return SpannableStringBuilder()
            .bold { append(senderName) }
            .append("  ")
            .append(caption)
    }
}
