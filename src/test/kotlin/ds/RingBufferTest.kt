package ds

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RingBufferTest {

    @Test
    fun `test RingBuffer`() {

        val buffer = RingBuffer<Int>()

        buffer.push(5)
        assertEquals(5, buffer.pop())
        assertEquals(null, buffer.pop())

        buffer.push(42)
        buffer.push(9)
        assertEquals(42, buffer.pop())
        assertEquals(9, buffer.pop())
        assertEquals(null, buffer.pop())

        buffer.push(42)
        buffer.push(9)
        buffer.push(12)
        assertEquals(12, buffer.get(2))
        assertEquals(9, buffer.get(1))
        assertEquals(42, buffer.get(0))

    }

}