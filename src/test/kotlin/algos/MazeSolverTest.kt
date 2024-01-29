package algos

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.Point
import util.drawPath

class MazeSolverTest {
    @Test
    fun `test maze solver`() {

        val maze = listOf(
            "xxxxxxxxxx x",
            "x        x x",
            "x        x x",
            "x xxxxxxxx x",
            "x          x",
            "x xxxxxxxxxx"
        )

        val mazeResult = listOf(
            Point(10, 0),
            Point(10, 1),
            Point(10, 2),
            Point(10, 3),
            Point(10, 4),
            Point(9, 4),
            Point(8, 4),
            Point(7, 4),
            Point(6, 4),
            Point(5, 4),
            Point(4, 4),
            Point(3, 4),
            Point(2, 4),
            Point(1, 4),
            Point(1, 5)
        )

        // there is only one path through
        val result = solve(maze, "x", Point(10, 0), Point(1, 5))

        assertEquals(drawPath(maze, result), drawPath(maze, mazeResult))

    }

}