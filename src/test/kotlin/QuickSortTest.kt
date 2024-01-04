import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class QuickSortTest {

    @Test
    fun `test quick sort`() {
        val arr = arrayOf(9, 3, 7, 4, 69, 420, 42)

        quickSort(arr)

        val expected = arrayOf(3, 4, 7, 9, 42, 69, 420)
        assertArrayEquals(expected, arr)
    }

}