package util

//      (1) --- (4) ---- (5)
//    /  |       |       /|
// (0)   | ------|------- |
//    \  |/      |        |
//      (2) --- (3) ---- (6)

val list1: WeightedAdjacencyList = arrayOf(
    arrayOf(
        GraphEdge(1, 3),
        GraphEdge(2, 1)
    ),
    arrayOf(
        GraphEdge(0, 3),
        GraphEdge(2, 4),
        GraphEdge(4, 1)
    ),
    arrayOf(
        GraphEdge(1, 4),
        GraphEdge(3, 7),
        GraphEdge(0, 1)
    ),
    arrayOf(
        GraphEdge(2, 7),
        GraphEdge(4, 5),
        GraphEdge(6, 1)
    ),
    arrayOf(
        GraphEdge(1, 1),
        GraphEdge(3, 5),
        GraphEdge(5, 2)
    ),
    arrayOf(
        GraphEdge(6, 1),
        GraphEdge(4, 2),
        GraphEdge(2, 18)
    ),
    arrayOf(
        GraphEdge(3, 1),
        GraphEdge(5, 1)
    )
)

//     >(1)<--->(4) ---->(5)
//    /          |       /|
// (0)     ------|------- |
//    \   v      v        v
//     >(2) --> (3) <----(6)

val list2: WeightedAdjacencyList = arrayOf(
    arrayOf(
        GraphEdge(1, 3),
        GraphEdge(2, 1)
    ),
    arrayOf(
        GraphEdge(4, 1)
    ),
    arrayOf(
        GraphEdge(3, 7)
    ),
    arrayOf(),
    arrayOf(
        GraphEdge(1, 1),
        GraphEdge(3, 5),
        GraphEdge(5, 2)
    ),
    arrayOf(
        GraphEdge(2, 18),
        GraphEdge(6, 1)
    ),
    arrayOf(
        GraphEdge(3, 1)
    )
)

val matrix2 = arrayOf(
    arrayOf(0, 3, 1, 0, 0, 0, 0), //0
    arrayOf(0, 0, 0, 0, 1, 0, 0),
    arrayOf(0, 0, 7, 0, 0, 0, 0),
    arrayOf(0, 0, 0, 0, 0, 0, 0),
    arrayOf(0, 1, 0, 5, 0, 2, 0),
    arrayOf(0, 0, 18, 0, 0, 0, 1),
    arrayOf(0, 0, 0, 1, 0, 0, 1)
)

fun drawPath(maze: List<String>, path: List<Point>): List<String> {

    val mazeCopy = maze.map { it.toMutableList() }

    path.forEach { point ->
        mazeCopy[point.y][point.x] = '*'
    }

    return mazeCopy.map { it.joinToString("") }
}
