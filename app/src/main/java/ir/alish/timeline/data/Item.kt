package ir.alish.timeline.data

data class Header(
    val senderAvatarUrl: String,
    val senderName: String,
    val location: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
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
    val size: Long,
    val width: Long,
    val height: Long
)

data class Post(
    val id: String,
    val header: Header,
    val content: PostContent
)

enum class PostType {
    TEXT,
    IMAGE,
    VIDEO
}