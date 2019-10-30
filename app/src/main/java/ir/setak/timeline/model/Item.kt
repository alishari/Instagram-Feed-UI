package ir.setak.timeline.model

/**
 * A model representing the content of post to show in recyclerview.
 */
data class Item(val id: String, val content: String, val details: String) {
    override fun toString(): String = content
}

data class Header(
    val senderAvatarUrl: String,
    val senderName: String,
    val location: String,
    val options: List<Option>
)

data class Option(
    val title: String,
    val actionId: String
)

data class PostContent(
    val type: PostType,
    val text: String,
    val mediaUrl: String,
    val mediaDetails: MediaDetails,
    val sendTime: Long,
    val likeCount: Long,
    val visitCount: Long
)

data class MediaDetails(
    val duration: Long,
    val size: Long
)

data class PostV2(
    val id: String,
    val header: Header
)

enum class PostType {
    TEXT,
    IMAGE,
    VIDEO
}