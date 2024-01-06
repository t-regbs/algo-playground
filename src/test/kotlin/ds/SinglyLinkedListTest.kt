package ds

import ds.SinglyLinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SinglyLinkedListTest {
    @Test
    fun `test SinglyLinkedList`() {
        val list = SinglyLinkedList<Int>()

        list.append(5)
        list.append(7)
        list.append(9)

        assertEquals(9, list.get(2))
        assertEquals(7, list.removeAt(1))
        assertEquals(2, list.length)

        list.append(11)
        assertEquals(9, list.removeAt(1))
        assertEquals(null, list.remove(9))
        assertEquals(5, list.removeAt(0))
        assertEquals(11, list.removeAt(0))
        assertEquals(0, list.length)

        list.prepend(5)
        list.prepend(7)
        list.prepend(9)

        assertEquals(5, list.get(2))
        assertEquals(9, list.get(0))
        assertEquals(9, list.remove(9))
        assertEquals(2, list.length)
        assertEquals(7, list.get(0))

    }
}
