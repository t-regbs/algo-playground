package algos.tree

import algos.tree.postOrderSearch
import org.junit.jupiter.api.Test
import util.tree
import kotlin.test.assertContentEquals

class BTPostOrderTest {
    @Test
    fun `test Post order search`() {

        val result = postOrderSearch(tree)
        assertContentEquals(
            arrayOf(
                7,
                5,
                15,
                10,
                29,
                45,
                30,
                100,
                50,
                20
            ),
            result
        )
    }
}