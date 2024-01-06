package algos.graph

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import util.GraphEdge
import util.list1

class PrimsListTest {
    @Test
    fun `test PrimsAlgorithm`() {

        // there is only one right answer for this graph
        val expected = arrayOf(
            arrayOf(
                GraphEdge(2, 1),
                GraphEdge(1, 3)
            ),
            arrayOf(
                GraphEdge(0, 3),
                GraphEdge(4, 1)
            ),
            arrayOf(GraphEdge(0, 1)),
            arrayOf(GraphEdge(6, 1)),
            arrayOf(
                GraphEdge(1, 1),
                GraphEdge(5, 2)
            ),
            arrayOf(
                GraphEdge(4, 2),
                GraphEdge(6, 1)
            ),
            arrayOf(
                GraphEdge(5, 1),
                GraphEdge(3, 1)
            )
        )

        assertArrayEquals(expected, prims(list1))
    }

}