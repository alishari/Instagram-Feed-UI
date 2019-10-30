package ir.setak.timeline.model

import java.util.ArrayList
import java.util.HashMap

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
    val ITEMS: MutableList<Item> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    private val ITEM_MAP: MutableMap<String, Item> = HashMap()

    private const val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                createDummyItem(
                    i
                )
            )
        }
    }

    private fun addItem(item: Item) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createDummyItem(position: Int): Item {
        return Item(
            position.toString(),
            "Item $position",
            makeDetails(position)
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
}
