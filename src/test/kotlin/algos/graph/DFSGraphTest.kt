package algos.graph

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import util.list2

class DFSGraphTest {

    @Test
    fun `test dfs graph list`() {
        assertArrayEquals(
            arrayOf(
                0,
                1,
                4,
                5,
                6
            ),
            dfs(list2, 0, 6)
        )
        assertArrayEquals(null, dfs(list2, 6, 0))
    }
}