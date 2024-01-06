package algos.tree

import algos.tree.preOrderSearch
import org.junit.jupiter.api.Test
import util.tree
import kotlin.test.assertContentEquals

class BTPreOrderTest {

    @Test
    fun `test Pre order search`() {

        val result = preOrderSearch(tree)
        assertContentEquals(
            arrayOf(
                20,
                10,
                5,
                7,
                15,
                50,
                30,
                29,
                45,
                100
            ),
            result
        )
    }
}