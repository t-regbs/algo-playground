package algos

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TwoCrystalBallsTest {
    @Test
    fun `test two crystal balls`() {

        val idx = (0..10000).random()
        val data = MutableList(10000) { false }

        for (i in idx..<10000) {
            data[i] = true
        }

        assertEquals(idx, twoCrystalBalls(data))

        val emptyData = List(821) { false }
        assertEquals(-1, twoCrystalBalls(emptyData))

    }

}