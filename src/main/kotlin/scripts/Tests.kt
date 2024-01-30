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
        FileSpec.builder(packageName, name).apply fileApply@ {
            addType(
                TypeSpec.classBuilder(name).apply typeApply@ {
                    val nameList = StringUtils.splitByCharacterTypeCamelCase(name.dropLast(4))
                    val className = nameList.joinToString("")
                    val funName = className.replaceFirstChar { it.lowercase(Locale.getDefault()) }
                    when (type) {
                        Sorting -> {
                            addImport(AssertionsPackage, "assertArrayEquals")
                            addImport(packageName, funName)
                            addFunction(
                                buildTestFunction(funName, buildSortingTestCode(funName))
                            )
                        }
                        Searching -> {
                            addImport(AssertionsPackage, "assertEquals")
                            addImport(packageName, funName)
                            addFunction(
                                buildTestFunction(funName, buildSearchTestCode(funName))
                            )
                        }
                        Graph -> {
                            addImport(AssertionsPackage, "assertArrayEquals")
                            if ("PrimsList" in name) {
                                addImport("util", "list1")
                                addImport("util", "GraphEdge")
                                addImport(packageName, funName.take(5).lowercase())
                                addFunction(
                                    buildTestFunction(funName, buildPrimsTestCode(funName.take(5).lowercase()))
                                )
                            } else if ("Dijkstra" in name) {
                                addImport("util", "list1")
                                addImport(AssertionsPackage, "assertArrayEquals")
                                addImport(packageName, funName)
                                addFunction(
                                    buildTestFunction(funName, buildDijkstraTestCode(funName))
                                )
                            } else {
                                addImport("util", "list2")
                                addImport(packageName, funName.take(3).lowercase())
                                addFunction(
                                    buildTestFunction(funName, buildGraphTestCode(funName.take(3).lowercase()))
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
                                    buildTestFunction(funName, MazeSolverTestCode)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.TwoCrystalBalls }Test") {
                                addImport(packageName, funName)
                                addFunction(
                                    buildTestFunction(funName, buildTwoCrystalBallsTestCode(funName))
                                )
                            }
                        }
                        DS -> {
                            if (name == "${ DataStructuresAndAlgorithms.Stack }Test") {
                                addImport(AssertionsPackage, "assertNull")
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildStackTestCode(className)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.Trie }Test") {
                                addImport("kotlin.test", "assertContentEquals")
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildTrieTestCode(className),
                                    hasAssertEqualsImport = false
                                )
                            }
                            if (name.contains("LinkedList")) {
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildLinkedListTestCode(className)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.RingBuffer }Test") {
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildRingBufferTestCode(className)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.Queue }Test") {
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildQueueTestCode(className)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.MinHeap }Test") {
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildMinHeapTestCode(className)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.Map }Test") {
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildMapTestCode(className)
                                )
                            }
                            if (name == "${ DataStructuresAndAlgorithms.LRU }Test") {
                                buildDataStructureTestCode(
                                    fileBuilder = this@fileApply,
                                    typeBuilder = this@typeApply,
                                    dsName = className,
                                    packageName = packageName,
                                    codeBlock = buildLRUTestCode(className)
                                )
                            }
                        }
                    }
                }.build()
            )
        }.build()
    }
}


fun buildTestFunction(className: String, codeBlock: String) =
    FunSpec.builder("test $className")
        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
        .addCode(codeBlock)
        .build()

fun buildDataStructureTestCode(
    fileBuilder: FileSpec.Builder,
    typeBuilder: TypeSpec.Builder,
    dsName: String,
    packageName: String,
    codeBlock: String,
    hasAssertEqualsImport: Boolean = true
) {
    fileBuilder.addImport(packageName, dsName)
    if (hasAssertEqualsImport) {
        fileBuilder.addImport(AssertionsPackage, "assertEquals")
    }
    typeBuilder.addFunction(
        buildTestFunction(dsName, codeBlock)
    )
}

private fun buildLRUTestCode(dsName: String) = """
    val lru = $dsName<String, Int>(3)

    assertEquals(null, lru.get("foo"))
    lru.update("foo", 69)
    assertEquals(69, lru.get("foo"))

    lru.update("bar", 420)
    assertEquals(420, lru.get("bar"))

    lru.update("baz", 1337)
    assertEquals(1337, lru.get("baz"))

    lru.update("ball", 69420)
    assertEquals(69420, lru.get("ball"))
    assertEquals(null, lru.get("foo"))
    assertEquals(420, lru.get("bar"))
    lru.update("foo", 69)
    assertEquals(420, lru.get("bar"))
    assertEquals(69, lru.get("foo"))

    // shouldn't of been deleted, but since bar was get'd, bar was added to the
    // front of the list, so baz became the end
    assertEquals(null, lru.get("baz"))
""".trimIndent()

private fun buildMapTestCode(dsName: String) = """
    val map = $dsName<String, Int>()

    map.set("foo", 55)
    assertEquals(1, map.size())

    map.set("fool", 75)
    assertEquals(2, map.size())

    map.set("foolish", 105)
    assertEquals(3, map.size())

    map.set("bar", 69)
    assertEquals(4, map.size())

    assertEquals(69, map.get("bar"))
    assertEquals(null, map.get("blaz"))

    map.delete("barblabr")
    assertEquals(4, map.size())

    map.delete("bar")
    assertEquals(3, map.size())
    assertEquals(null, map.get("bar"))
""".trimIndent()

private fun buildMinHeapTestCode(dsName: String) = """
    val heap = $dsName()

    assertEquals(0, heap.length)

    heap.insert(5)
    heap.insert(3)
    heap.insert(69)
    heap.insert(420)
    heap.insert(4)
    heap.insert(1)
    heap.insert(8)
    heap.insert(7)

    assertEquals(8, heap.length)
    assertEquals(1, heap.delete())
    assertEquals(3, heap.delete())
    assertEquals(4, heap.delete())
    assertEquals(5, heap.delete())
    assertEquals(4, heap.length)
    assertEquals(7, heap.delete())
    assertEquals(8, heap.delete())
    assertEquals(69, heap.delete())
    assertEquals(420, heap.delete())
    assertEquals(0, heap.length)
""".trimIndent()

private fun buildQueueTestCode(dsName: String) = """
    val queue = $dsName<Int>()

    queue.enqueue(5)
    queue.enqueue(7)
    queue.enqueue(9)

    assertEquals(5, queue.deque())
    assertEquals(2, queue.length)

    queue.enqueue(11)

    assertEquals(7, queue.deque())
    assertEquals(9, queue.deque())
    assertEquals(11, queue.peek())
    assertEquals(11, queue.deque())
    assertEquals(null, queue.deque())
    assertEquals(0, queue.length)

    queue.enqueue(69)
    assertEquals(69, queue.peek())
    assertEquals(1, queue.length)
""".trimIndent()

private fun buildRingBufferTestCode(dsName: String) = """
    val buffer = $dsName<Int>()

    buffer.push(5)
    assertEquals(5, buffer.pop())
    assertEquals(null, buffer.pop())

    buffer.push(42)
    buffer.push(9)
    assertEquals(42, buffer.pop())
    assertEquals(9, buffer.pop())
    assertEquals(null, buffer.pop())

    buffer.push(42)
    buffer.push(9)
    buffer.push(12)
    assertEquals(12, buffer.get(2))
    assertEquals(9, buffer.get(1))
    assertEquals(42, buffer.get(0))
""".trimIndent()

private fun buildLinkedListTestCode(dsName: String) = """
    val list = $dsName<Int>()

    list.append(5)
    list.append(7)
    list.append(9)

    assertEquals(9, list.get(2))
    assertEquals(7, list.removeAt(1))
    assertEquals(2, list.length)

    list.append(11)
    assertEquals(9, list.removeAt(1))
    assertEquals(null, list.remove(9))
    assertEquals(5, list.removeAt(0))
    assertEquals(11, list.removeAt(0))
    assertEquals(0, list.length)

    list.prepend(5)
    list.prepend(7)
    list.prepend(9)

    assertEquals(5, list.get(2))
    assertEquals(9, list.get(0))
    assertEquals(9, list.remove(9))
    assertEquals(2, list.length)
    assertEquals(7, list.get(0))
""".trimIndent()

private fun buildTrieTestCode(dsName: String) = """
    val trie = $dsName()

    trie.insert("foo")
    trie.insert("fool")
    trie.insert("foolish")
    trie.insert("bar")

    assertContentEquals(arrayOf("foo", "fool", "foolish").sorted(), trie.find("fo").sorted())

    trie.delete("fool")

    assertContentEquals(arrayOf("foo", "foolish").sorted(), trie.find("fo").sorted())
""".trimIndent()
private fun buildStackTestCode(dsName: String) = """
    val stack = $dsName<Int>()

    stack.push(5)
    stack.push(7)
    stack.push(9)

    assertEquals(9, stack.pop())
    assertEquals(2, stack.length)

    stack.push(11)
    assertEquals(11, stack.pop())
    assertEquals(7, stack.pop())
    assertEquals(5, stack.peek())
    assertEquals(5, stack.pop())
    assertNull(stack.pop())

    // Make sure can handle empty stack
    stack.push(69)
    assertEquals(69, stack.peek())
    assertEquals(1, stack.length)
""".trimIndent()

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

private fun buildDijkstraTestCode(funName: String) = """
    val expected = arrayOf(0, 1, 4, 5, 6)
    assertArrayEquals(expected, $funName(0, 6, list1))
""".trimIndent()

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