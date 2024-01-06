package ds

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.ILRU

class LRUTest {

    @Test
    fun `test LRU`() {

        val lru = LRU<String, Int>(3)

        assertEquals(null, lru.get("foo"))
        lru.update("foo", 69)
        assertEquals(69, lru.get("foo"))

        lru.update("bar", 420)
        assertEquals(420, lru.get("bar"))

        lru.update("baz", 1337)
        assertEquals(1337, lru.get("baz"))

        lru.update("ball", 69420)
        assertEquals(69420, lru.get("ball"))
        assertEquals(null, lru.get("foo"))
        assertEquals(420, lru.get("bar"))
        lru.update("foo", 69)
        assertEquals(420, lru.get("bar"))
        assertEquals(69, lru.get("foo"))

        // shouldn't of been deleted, but since bar was get'd, bar was added to the
        // front of the list, so baz became the end
        assertEquals(null, lru.get("baz"))

    }

}