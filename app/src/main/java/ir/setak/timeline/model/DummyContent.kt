package ir.setak.timeline.model

import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<PostV2> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    private val ITEM_MAP: MutableMap<String, PostV2> = HashMap()

    private const val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                createMockItem(
                    i
                )
            )
        }
    }

    private fun addItem(item: PostV2) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createMockItem(position: Int): PostV2 {
        when {
            position % 2 == 0 -> return PostV2(
                position.toString(), Header(
                    "https://scontent-frx5-1.cdninstagram.com/vp/16a8ccffdef68aefc3cb9eee651b6bef/5E5C65A6/t51.2885-19/s150x150/71518971_590933194776066_4042702460588916736_n.jpg?_nc_ht=scontent-frx5-1.cdninstagram.com",
                    "selenagomez",
                    "",
                    options = listOf(Option("It is a spam", "1"))
                ), PostContent(
                    PostType.IMAGE,
                    "You promised the world.",
                    "https://instagram.fevn1-1.fna.fbcdn.net/vp/51e58a7ebb0c20cd1ead49a0af9e6838/5E59E776/t51.2885-15/e35/s1080x1080/75210501_164285214687742_4699631490916654156_n.jpg?_nc_ht=instagram.fevn1-1.fna.fbcdn.net&_nc_cat=1",
                    MediaDetails(-1, 100, 640, 637),
                    System.currentTimeMillis(), 8420661, 8420661 * 3
                )
            )
            else -> return PostV2(
                position.toString(), Header(
                    "https://instagram.fevn1-4.fna.fbcdn.net/vp/e33ecbef2410e029b788e0c37a62f695/5E52D965/t51.2885-19/s150x150/66510947_638381409990212_7768244753421828096_n.jpg?_nc_ht=instagram.fevn1-4.fna.fbcdn.net",
                    "taylorswift",
                    "Nashville, Tennessee",
                    options = listOf(Option("It is a spam", "1"))
                ), PostContent(
                    PostType.IMAGE,
                    "Hug your cat today. Or don’t, if your cat hates hugs. But anyway. Happy #NationalCatDay from me, Olivia, Meredith, and Benjamin. \uD83D\uDE38\uD83D\uDE38\uD83D\uDE38\uD83D\uDC71\uD83C\uDFFB\u200D♀️",
                    "https://instagram.fevn1-1.fna.fbcdn.net/vp/b17bc3540202047079ca5166ad78c8b0/5E4ED780/t51.2885-15/e35/p1080x1080/70650511_423676981664300_5850008591680918808_n.jpg?_nc_ht=instagram.fevn1-1.fna.fbcdn.net&_nc_cat=1",
                    MediaDetails(-1, 100, 640, 800),
                    System.currentTimeMillis(), 1807203, 1807203 * 3
                )
            )
        }
    }

}
