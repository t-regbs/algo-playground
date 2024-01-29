package scripts

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import org.apache.commons.lang3.StringUtils
import scripts.TestType.*
import util.DataStructuresAndAlgorithms
import java.util.*

fun main(args: Array<String>) {
    val packageName = "day${args.first().toInt()}"
    val tests = args
        .takeLast(args.size - 1)
        .map { AlgoTests.valueOf(it) }
        .map { buildTest(packageName, it.test ) }
    tests.forEach {
        println("----${it.name}----")
        it.writeTo(System.out)
    }
}

data class Test(
    val name: String,
    val type: TestType
)

enum class TestType {
    Sorting, Searching, Graph, Tree, Other, DS
}

fun buildTest(packageName: String, test: Test): FileSpec {
    return with(test) {
        FileSpec.builder(packageName, name).apply {
            addType(
                TypeSpec.classBuilder(name).apply {
                    val nameList = StringUtils.splitByCharacterTypeCamelCase(name.dropLast(4))
                    val funName = nameList.joinToString("").replaceFirstChar { it.lowercase(Locale.getDefault()) }
                    when (type) {
                        Sorting -> {
                            addImport(AssertionsPackage, "assertArrayEquals")
                            addImport(packageName, funName)
                            addFunction(
                                FunSpec.builder("test $funName")
                                    .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                    .addCode(
                                        buildSortingTestCode(funName)
                                    )
                                    .build()
                            )
                        }
                        Searching -> {
                            addImport(AssertionsPackage, "assertEquals")
                            addImport(packageName, funName)
                            addFunction(
                                FunSpec.builder("test $funName")
                                    .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                    .addCode(
                                        buildSearchTestCode(funName)
                                    )
                                    .build()
                            )
                        }
                        Graph -> {
                            addImport(AssertionsPackage, "assertArrayEquals")
                            if ("PrimsList" !in name) {
                                addImport("util", "list2")
                                addImport(packageName, funName.take(3).lowercase())
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(
                                            buildGraphTestCode(funName.take(3).lowercase())
                                        )
                                        .build()
                                )
                            } else {
                                addImport("util", "list1")
                                addImport("util", "GraphEdge")
                                addImport(packageName, funName.take(5).lowercase())
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(
                                            buildPrimsTestCode(funName.take(5).lowercase())
                                        )
                                        .build()
                                )
                            }
                        }
                        Tree -> {
                            addImport("util", "tree")
                            if ("Compare" in name) {
                                addImport("util", "tree2")
                                addImport(packageName, "compare")
                                addImport("kotlin.test", "assertEquals")
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(
                                            """
                                                assertEquals(true, compare(tree, tree))
                                                assertEquals(false, compare(tree, tree2))
                                            """.trimIndent()
                                        )
                                        .build()
                                )
                            }
                            if ("Order" in name) {
                                val callName =
                                    "${ funName.drop(2).replaceFirstChar { it.lowercase(Locale.getDefault()) } }Search"
                                val arraycode = when (name) {
                                    "${ DataStructuresAndAlgorithms.BTInOrder }Test" -> InOrderArray
                                    "${ DataStructuresAndAlgorithms.BTPostOrder }Test" -> PostOrderArray
                                    "${ DataStructuresAndAlgorithms.BTPreOrder }Test" -> PreOrderArray
                                    else -> ""
                                }
                                addImport("kotlin.test", "assertContentEquals")
                                addImport(packageName, callName)
                                addFunction(
                                    FunSpec.builder("test $callName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(
                                            """
                                                val result = $callName(tree)
                                                assertContentEquals(
                                                    $arraycode
                                                    result
                                                )
                                            """.trimIndent()
                                        )
                                        .build()
                                )
                            }
                            if (name == "${DataStructuresAndAlgorithms.DFSOnBST}Test") {
                                addImport("kotlin.test", "assertEquals")
                                addImport(packageName, "dfsTree")
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(
                                            """
                                            assertEquals(true, dfsTree(tree, 45))
                                            assertEquals(true, dfsTree(tree, 7))
                                            assertEquals(false, dfsTree(tree, 69))
                                            """.trimIndent()
                                        )
                                        .build()
                                )
                            }
                            if (name == "${DataStructuresAndAlgorithms.BTBFS}Test") {
                                addImport("kotlin.test", "assertEquals")
                                addImport(packageName, "bfsTree")
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(
                                            """
                                            assertEquals(true, bfsTree(tree, 45))
                                            assertEquals(true, bfsTree(tree, 7))
                                            assertEquals(false, bfsTree(tree, 69))
                                            """.trimIndent()
                                        )
                                        .build()
                                )
                            }
                        }
                        Other -> {
                            addImport(AssertionsPackage, "assertEquals")
                            if (name == "${ DataStructuresAndAlgorithms.MazeSolver }Test") {
                                addImport("util", "Point")
                                addImport("util", "drawPath")
                                addImport(packageName, "solve")
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(MazeSolverTestCode)
                                        .build()
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.TwoCrystalBalls }Test") {
                                addImport(packageName, funName)
                                addFunction(
                                    FunSpec.builder("test $funName")
                                        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                                        .addCode(buildTwoCrystalBallsTestCode(funName))
                                        .build()
                                )
                            }
                        }
                        else -> {}
                    }
                }.build()
            )
        }.build()
    }
}

private fun buildSearchTestCode(funName: String) = """
                                        |val foo = arrayOf(1, 3, 4, 69, 71, 81, 90, 99, 420, 1337, 69420)
                                        |
                                        |assertEquals(true, $funName(foo, 69))
                                        |assertEquals(false,$funName(foo, 1336))
                                        |assertEquals(true, $funName(foo, 69420))
                                        |assertEquals(false,$funName(foo, 69421))
                                        |assertEquals(true, $funName(foo, 1))
                                        |assertEquals(false,$funName(foo, 0))
                                        """.trimMargin()

private fun buildSortingTestCode(funName: String) = """
                                        |val arr = arrayOf(9, 3, 7, 4, 69, 420, 42)
                                        |
                                        |$funName(arr) 
                                        |
                                        |val expected = arrayOf(3, 4, 7, 9, 42, 69, 420)
                                        |assertArrayEquals(expected, arr)
                                        """.trimMargin()

private fun buildTwoCrystalBallsTestCode(funName: String) = """
    |val idx = (0..10000).random()
    |    val data = MutableList(10000) { false }
    |    for (i in idx..<10000) {
    |        data[i] = true
    |    }
    |    assertEquals(idx, $funName(data))
    |    val emptyData = List(821) { false }
    |    assertEquals(-1, $funName(emptyData))
""".trimMargin()
private fun buildGraphTestCode(funName: String) = """
    |assertArrayEquals(
    |    arrayOf(
    |        0,
    |        1,
    |        4,
    |        5,
    |        6,
    |    ),
    |    $funName(list2, 0, 6)
    |)
    |assertArrayEquals(null, $funName(list2, 6, 0))
""".trimMargin()

private fun buildPrimsTestCode(funName: String) = """
    |// there is only one right answer for this graph
    |val expected = arrayOf(
    |    arrayOf(
    |        GraphEdge(2, 1),
    |        GraphEdge(1, 3)
    |    ),
    |    arrayOf(
    |        GraphEdge(0, 3),
    |        GraphEdge(4, 1)
    |    ),
    |    arrayOf(GraphEdge(0, 1)),
    |    arrayOf(GraphEdge(6, 1)),
    |    arrayOf(
    |        GraphEdge(1, 1),
    |        GraphEdge(5, 2)
    |    ),
    |    arrayOf(
    |        GraphEdge(4, 2),
    |        GraphEdge(6, 1)
    |    ),
    |    arrayOf(
    |        GraphEdge(5, 1),
    |        GraphEdge(3, 1)
    |    )
    |)
    |assertArrayEquals(expected, $funName(list1))
""".trimMargin()

val InOrderArray = """
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
""".trimIndent()

val PreOrderArray = """
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
""".trimIndent()

val PostOrderArray = """
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
""".trimIndent()

val MazeSolverTestCode = """
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
""".trimIndent()

const val AssertionsPackage = "org.junit.jupiter.api.Assertions"