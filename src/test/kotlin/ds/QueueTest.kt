package ds

import ds.Queue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QueueTest {

    @Test
    fun `test queue`() {
        val queue = Queue<Int>()

        queue.enqueue(5)
        queue.enqueue(7)
        queue.enqueue(9)

        assertEquals(5, queue.deque())
        assertEquals(2, queue.length)

        queue.enqueue(11)

        assertEquals(7, queue.deque())
        assertEquals(9, queue.deque())
        assertEquals(11, queue.peek())
        assertEquals(11, queue.deque())
        assertEquals(null, queue.deque())
        assertEquals(0, queue.length)

        queue.enqueue(69)
        assertEquals(69, queue.peek())
        assertEquals(1, queue.length)
    }

}