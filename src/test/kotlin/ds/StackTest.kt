package ds

import ds.Stack
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class StackTest {

    @Test
    fun `test stack`() {

        val stack = Stack<Int>()

        stack.push(5)
        stack.push(7)
        stack.push(9)

        assertEquals(9, stack.pop())
        assertEquals(2, stack.length)

        stack.push(11)
        assertEquals(11, stack.pop())
        assertEquals(7, stack.pop())
        assertEquals(5, stack.peek())
        assertEquals(5, stack.pop())
        assertNull(stack.pop())

        // Make sure can handle empty stack
        stack.push(69)
        assertEquals(69, stack.peek())
        assertEquals(1, stack.length)
    }

}
