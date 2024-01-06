package algos.tree

import org.junit.jupiter.api.Test
import util.tree
import kotlin.test.assertEquals

class BTBFSTest {

    @Test
    fun `test bfs`() {
        assertEquals(true, bfs(tree, 45))
        assertEquals(true, bfs(tree, 7))
        assertEquals(false, bfs(tree, 69))
    }
}