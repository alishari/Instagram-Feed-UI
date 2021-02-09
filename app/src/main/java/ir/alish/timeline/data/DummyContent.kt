package ir.alish.timeline.data

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
    val ITEMS: MutableList<Post> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    private val ITEM_MAP: MutableMap<String, Post> = HashMap()

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

    private fun addItem(item: Post) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createMockItem(position: Int): Post {
        when {
            position % 3 == 0 -> return Post(
                position.toString(), Header(
                    "https://instagram.fbtz1-7.fna.fbcdn.net/v/t51.2885-19/s320x320/142428646_741794190097792_7655066869999087337_n.jpg?_nc_ht=instagram.fbtz1-7.fna.fbcdn.net&_nc_ohc=f7hUY8pqa-AAX_kTmQy&tp=1&oh=e178b34e7de91e98e786920bebc5bc82&oe=604B8E11",
                    "selenagomez",
                    "",
                    options = listOf(Option("It is a spam", "1"))
                ), PostContent(
                    PostType.IMAGE,
                    "You promised the world.",
                    "https://instagram.fbtz1-7.fna.fbcdn.net/v/t51.2885-15/e35/p1080x1080/140850667_4352638434769381_1685919934556205424_n.jpg?_nc_ht=instagram.fbtz1-7.fna.fbcdn.net&_nc_cat=1&_nc_ohc=wMyC1Umf50gAX_A6EBE&tp=1&oh=0d3e02bbef3bb5b6646813f5276a37e8&oe=604C5CED",
                    MediaDetails(-1, 100, 640, 637),
                    System.currentTimeMillis(), 8420661, 8420661 * 3
                )
            )
            position % 3 == 1 -> return Post(
                position.toString(), Header(
                    "https://instagram.fbtz1-7.fna.fbcdn.net/v/t51.2885-19/s320x320/39934387_279822176078020_191712891823456256_n.jpg?_nc_ht=instagram.fbtz1-7.fna.fbcdn.net&_nc_ohc=6xFnX9Udg1wAX9V5ykj&tp=1&oh=c755fb004007f71c509da94c0ff9e114&oe=604AE99A",
                    "living_destinations",
                    "Germany",
                    options = listOf(Option("It is a spam", "1"))
                ), PostContent(
                    PostType.IMAGE,
                    "The woods of fairytales ~ Germany\n" +
                            "\n" +
                            "Photo: @aachendeutschland Congrats! \uD83D\uDE0D ➡ Founders: @ournextflight ⬅\n" +
                            "TAG someone you love \uD83D\uDC47❤️ #living_europe #naturelovers #nature_shooters #nature #travel_drops #ig_deutschland #bestplacestogo #germany #deutschland_greatshots #exploring_shotz #germanytourism #places_wow #visitgermany #cityscape #cityview #traveladdict #travelphotography #postcardsfromtheworld #exploring_shotz #placestosee #loves_germany #autumnlove #nature_perfection",
                    "https://instagram.fbtz1-5.fna.fbcdn.net/v/t51.2885-15/sh0.08/e35/c0.128.1025.1025a/s640x640/146054108_847700856025359_1622312884830452113_n.jpg?_nc_ht=instagram.fbtz1-5.fna.fbcdn.net&_nc_cat=103&_nc_ohc=leQUYH45JqQAX8shuzz&tp=1&oh=5a900b8257b65fdb755a283aa06f68f5&oe=604BE452",
                    MediaDetails(-1, 100, 640, 795),
                    System.currentTimeMillis(), 23314, 23314 * 3
                )
            )
            else -> return Post(
                position.toString(), Header(
                    "https://instagram.fbtz1-7.fna.fbcdn.net/v/t51.2885-19/s150x150/130263346_142044591035601_703732503172625708_n.jpg?_nc_ht=instagram.fbtz1-7.fna.fbcdn.net&_nc_ohc=nmzG2-fLd88AX_Rleot&tp=1&oh=979c2ab114208eaf13e49531f10c135f&oe=604C342C",
                    "taylorswift",
                    "Nashville, Tennessee",
                    options = listOf(Option("It is a spam", "1"))
                ), PostContent(
                    PostType.IMAGE,
                    "Hug your cat today. Or don’t, if your cat hates hugs. But anyway. Happy #NationalCatDay from me, Olivia, Meredith, and Benjamin. \uD83D\uDE38\uD83D\uDE38\uD83D\uDE38\uD83D\uDC71\uD83C\uDFFB\u200D♀️",
                    "https://instagram.fbtz1-7.fna.fbcdn.net/v/t51.2885-15/sh0.08/e35/s640x640/130868644_679944582713916_5486154884800449761_n.jpg?_nc_ht=instagram.fbtz1-7.fna.fbcdn.net&_nc_cat=1&_nc_ohc=BtuctLJ5aDAAX9-AUXe&tp=1&oh=58b8ce9e363979496f5e6c638cc99faf&oe=604A4CAC",
                    MediaDetails(-1, 100, 640, 800),
                    System.currentTimeMillis(), 1807203, 1807203 * 3
                )
            )
        }
    }

}
