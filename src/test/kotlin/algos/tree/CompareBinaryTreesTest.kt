package algos.tree

import algos.tree.compare
import org.junit.jupiter.api.Test
import util.tree
import util.tree2
import kotlin.test.assertEquals

class CompareBinaryTreesTest {

    @Test
    fun `test compare binary trees`() {
        assertEquals(true, compare(tree, tree))
        assertEquals(false, compare(tree, tree2))
    }
}