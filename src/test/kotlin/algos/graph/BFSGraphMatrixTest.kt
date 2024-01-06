package algos.graph

import algos.graph.bfs
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import util.matrix2

class BFSGraphMatrixTest {

    @Test
    fun `test bfs graph matrix`() {
        assertArrayEquals(
            arrayOf(
                0,
                1,
                4,
                5,
                6
            ),
            bfs(matrix2, 0, 6)
        )
        assertArrayEquals(null, bfs(matrix2, 6, 0))
    }
}