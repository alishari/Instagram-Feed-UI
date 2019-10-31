package ir.setak.timeline.ui.adapter


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.setak.timeline.model.PostV2
import ir.setak.timeline.ui.fragment.PostFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.post_content.view.*
import kotlinx.android.synthetic.main.post_header.view.*
import android.widget.PopupMenu
import ir.setak.timeline.util.DimensionUtils
import android.util.DisplayMetrics
import android.app.Activity
import com.example.timeline.R
import ir.setak.timeline.util.NumberUtil
import ir.setak.timeline.util.TimeUtil
import kotlin.math.ceil
import kotlin.math.roundToInt


/**
 * [RecyclerView.Adapter] that can display a [PostV2] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */

const val DEFAULT_LINES = 2
const val MAX_LINES = 20

class PostAdapter(
    private val mValues: List<PostV2>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PostAdapter.PostVH>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener {
            val item = it.tag as PostV2
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
        var time = System.currentTimeMillis()
        holder.setHeader(item)
        println("set header time : " + (System.currentTimeMillis() - time))
        time = System.nanoTime()
        holder.setContent(item)
        println("set content time: " + (System.currentTimeMillis() - time))
    }

    private fun PostVH.setContent(item: PostV2) {
        val caption = item.content.text
        val displayMetrics = DisplayMetrics()
        val activity = postImage.context as Activity
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        postImage.minimumHeight =
            (width.toFloat() * item.content.mediaDetails.height / item.content.mediaDetails.width).roundToInt() + 1
        Glide.with(activity)
            .load(item.content.mediaUrl)
            .thumbnail(0.1f)
            .into(postImage)

        val senderName = item.header.senderName

        likeCount.text =
            likeCount.context.getString(R.string.likes, NumberUtil.format(item.content.likeCount))

        captionText.post {
            setCaption(senderName, caption)
            captionText.setOnClickListener {
                captionText.let {
                    it.maxLines = MAX_LINES
                    it.text = getFullCaption(senderName, caption)
                }
            }
        }
        with(mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }

        sendTime.text = TimeUtil.format(item.content.sendTime)
    }

    private fun PostVH.setHeader(item: PostV2) {
        senderName.text = item.header.senderName

        val gD = GradientDrawable()
        gD.setColor(Color.LTGRAY)
        gD.shape = GradientDrawable.OVAL

        Glide.with(senderAvatar.context)
            .load(item.header.senderAvatarUrl)
            .thumbnail(0.1f)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(gD)
            .into(senderAvatar)

        item.header.location.let {
            senderLocation.visibility = if (it.isEmpty()) {
                View.GONE
            } else {
                senderLocation.text = it
                View.VISIBLE
            }
        }

        optionsImageView.setOnClickListener {
            val popup = PopupMenu(it.context, it)
            item.header.options.let { options ->
                for (option in options) {
                    popup.menu.add(option.title).setOnMenuItemClickListener {
                        println("Action : ${option.actionId}")
                        true
                    }
                }
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class PostVH(val mView: View) : RecyclerView.ViewHolder(mView) {
        val senderAvatar: ImageView = mView.sender_avatar
        val optionsImageView: ImageView = mView.options
        val senderName: TextView = mView.sender_name
        val senderLocation: TextView = mView.sender_location
        val captionText: TextView = mView.caption_text
        val postImage: ImageView = mView.post_image
        val likeCount: TextView = mView.like_count
        val sendTime: TextView = mView.send_time

    }

    private fun PostVH.setCaption(senderName: String, caption: String) {
        val context = captionText.context
        captionText.text = getFullCaption(senderName, caption)

        if (captionText.lineCount > DEFAULT_LINES) {
            val lastCharShown = captionText.layout.getLineVisibleEnd(DEFAULT_LINES - 1)
            captionText.maxLines = DEFAULT_LINES
            val moreString = context.getString(R.string.read_more)
            val suffix = " $moreString"
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
