package algos.searching

import algos.searching.linearSearch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinearSearchTest {
    @Test
    fun `linear search array`() {

        val foo = arrayOf(1, 3, 4, 69, 71, 81, 90, 99, 420, 1337, 69420)

        assertEquals(true, linearSearch(foo, 69))
        assertEquals(false, linearSearch(foo, 1336))
        assertEquals(true, linearSearch(foo, 69420))
        assertEquals(false, linearSearch(foo, 69421))
        assertEquals(true, linearSearch(foo, 1))
        assertEquals(false, linearSearch(foo, 0))

    }
}