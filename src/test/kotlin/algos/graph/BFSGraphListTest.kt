package algos.graph

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import util.list2

class BFSGraphListTest {

    @Test
    fun `test bfs graph list`() {
        assertArrayEquals(
            arrayOf(
                0,
                1,
                4,
                5,
                6,
            ),
            bfs(list2, 0, 6)
        )
        assertArrayEquals(null, bfs(list2, 6, 0))
    }
}