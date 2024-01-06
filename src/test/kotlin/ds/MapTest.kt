package ds

import ds.Map
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MapTest {
    @Test
    fun `test map`() {

        val map = Map<String, Int>()

        map.set("foo", 55)
        assertEquals(1, map.size())

        map.set("fool", 75)
        assertEquals(2, map.size())

        map.set("foolish", 105)
        assertEquals(3, map.size())

        map.set("bar", 69)
        assertEquals(4, map.size())

        assertEquals(69, map.get("bar"))
        assertEquals(null, map.get("blaz"))

        map.delete("barblabr")
        assertEquals(4, map.size())

        map.delete("bar")
        assertEquals(3, map.size())
        assertEquals(null, map.get("bar"))

    }

}