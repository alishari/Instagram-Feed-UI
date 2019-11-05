package ir.alish.timeline.ui.timeline


import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.SpannableStringBuilder
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import ir.alish.timeline.R
import ir.alish.timeline.data.Post
import ir.alish.timeline.ui.timeline.PostFragment.OnListFragmentInteractionListener
import ir.alish.timeline.utils.NumberUtil
import ir.alish.timeline.utils.TimeUtil
import kotlinx.android.synthetic.main.post_content.view.*
import kotlinx.android.synthetic.main.post_header.view.*
import kotlin.math.roundToInt


/**
 * [RecyclerView.Adapter] that can display a [Post] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */

const val DEFAULT_LINES = 2
const val MAX_LINES = 20

class PostAdapter(
    private val mValues: List<Post>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PostAdapter.PostVH>() {

    private val headerTime = ArrayList<Long>()
    private val contentTime = ArrayList<Long>()

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener {
            val item = it.tag as Post
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
        headerTime.add((System.currentTimeMillis() - time))
        println("set header average time : " + headerTime.average())
        time = System.currentTimeMillis()
        holder.setContent(item)
        contentTime.add((System.currentTimeMillis() - time))
        println("set content average time: " + contentTime.average())
    }

    private fun PostVH.setContent(item: Post) {
        val caption = item.content.text
        val displayMetrics = DisplayMetrics()
        val activity = postImage.context as Activity
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        postImage.minimumHeight =
            (width.toFloat() * item.content.mediaDetails.height / item.content.mediaDetails.width).roundToInt() + 1
        postImage.load(item.content.mediaUrl) {
            crossfade(true)
        }

        val senderName = item.header.senderName

        likeCount.text =
            likeCount.context.getString(R.string.likes, NumberUtil.format(item.content.likeCount))

        captionText.setExpandableText(senderName, caption)
//        captionText.post {
//            captionText.setCaption(senderName, caption)
//            captionText.setOnClickListener {
//                captionText.let {
//                    it.maxLines = MAX_LINES
//                    it.text = getFullCaption(senderName, caption)
//                }
//            }
//        }
        with(mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }

        sendTime.text = TimeUtil.format(item.content.sendTime)
    }

    private fun TextView.setExpandableText(senderName: String, caption: String) {
        post {
            setCaption(senderName, caption)
            setOnClickListener {
                let {
                    it.maxLines = MAX_LINES
                    it.text = getFullCaption(senderName, caption)
                }
            }
        }
    }

    private fun PostVH.setHeader(item: Post) {
        senderName.text = item.header.senderName

        val gD = GradientDrawable()
        gD.setColor(Color.LTGRAY)
        gD.shape = GradientDrawable.OVAL

        senderAvatar.load(item.header.senderAvatarUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
            placeholder(gD)
        }

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

    private fun TextView.setCaption(senderName: String, caption: String) {
        text = getFullCaption(senderName, caption)

        if (lineCount > DEFAULT_LINES) {
            val lastCharShown = layout.getLineVisibleEnd(DEFAULT_LINES - 1)
            maxLines = DEFAULT_LINES
            val moreString = context.getString(R.string.read_more)
            val suffix = " $moreString"
            // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
            val actionDisplayText = context.getString(R.string.more_dots) + suffix

            text = SpannableStringBuilder()
                .bold { append(senderName) }
                .append("  ")
                .append(
                    caption.substring(
                        0,
                        lastCharShown - suffix.length - 3 - moreString.length - senderName.length
                    )
                )
                .color(Color.GRAY) { append(actionDisplayText) }
        }
    }

    private fun getFullCaption(
        senderName: String,
        caption: String
    ) = SpannableStringBuilder()
        .bold { append(senderName) }
        .append("  ")
        .append(caption)
}
