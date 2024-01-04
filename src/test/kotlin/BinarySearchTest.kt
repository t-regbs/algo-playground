import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BinarySearchTest {

    @Test
    fun `binary search array`() {

        val foo = arrayOf(1, 3, 4, 69, 71, 81, 90, 99, 420, 1337, 69420)

        assertEquals(true, binarySearch(foo, 69))
        assertEquals(false, binarySearch(foo, 1336))
        assertEquals(true, binarySearch(foo, 69420))
        assertEquals(false, binarySearch(foo, 69421))
        assertEquals(true, binarySearch(foo, 1))
        assertEquals(false, binarySearch(foo, 0))

    }
}