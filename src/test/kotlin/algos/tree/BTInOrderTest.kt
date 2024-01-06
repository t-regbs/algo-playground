package algos.tree

import algos.tree.inOrderSearch
import org.junit.jupiter.api.Test
import util.tree
import kotlin.test.assertContentEquals

class BTInOrderTest {
    @Test
    fun `test In order search`() {

        val result = inOrderSearch(tree)
        assertContentEquals(
            arrayOf(
                5,
                7,
                10,
                15,
                20,
                29,
                30,
                45,
                50,
                100
            ),
            result
        )
    }
}