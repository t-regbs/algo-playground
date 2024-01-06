package algos.tree

import org.junit.jupiter.api.Test
import util.tree
import kotlin.test.assertEquals

class DFSOnBSTTest {

    @Test
    fun `test dfs on bst`() {
        assertEquals(true, dfs(tree, 45))
        assertEquals(true, dfs(tree, 7))
        assertEquals(false, dfs(tree, 69))
    }
}