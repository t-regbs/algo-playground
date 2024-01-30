package scripts

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import util.BinaryNode
import util.DataStructuresAndAlgorithms

enum class Algos(
    val algoType: Dsa
) {
    BubbleSort(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BubbleSort.name,
            name = "bubbleSort",
            args = sortingArgs
        )
    ),
    InsertionSort(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.InsertionSort.name,
            name = "insertionSort",
            args = sortingArgs
        )
    ),
    MergeSort(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.MergeSort.name,
            name = "mergeSort",
            args = sortingArgs
        )
    ),
    QuickSort(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.QuickSort.name,
            name = "quickSort",
            args = sortingArgs
        )
    ),
    BinarySearch(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BinarySearch.name,
            name = "binarySearch",
            args = searchingArgs,
            returnType = Boolean::class.asTypeName()
        )
    ),
    LinearSearch(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.LinearSearch.name,
            name = "linearSearch",
            args = searchingArgs,
            returnType = Boolean::class.asTypeName()
        )
    ),
    BFSGraphList(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BFSGraphList.name,
            name = "bfs",
            args = graphSearchArgs,
            returnType = ArrayOfIntsTypeName.copy(true)
        )
    ),
    BFSGraphMatrix(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BFSGraphMatrix.name,
            name = "bfs",
            args = graphMatrixArgs,
            returnType = ArrayOfIntsTypeName.copy(true)
        )
    ),
    DFSGraphList(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.DFSGraphList.name,
            name = "dfs",
            args = graphSearchArgs,
            returnType = ArrayOfIntsTypeName.copy(true)
        )
    ),
    DijkstraList(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.DijkstraList.name,
            name = "dijkstraList",
            args = listOf(
                "source" to Int::class.asTypeName(),
                "sink" to Int::class.asTypeName(),
                "arr" to WeightedAdjacencyListClassName
            ),
            returnType = ArrayOfIntsTypeName
        )
    ),
    PrimsList(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.PrimsList.name,
            name = "prims",
            args = listOf("list" to WeightedAdjacencyListClassName),
            returnType = WeightedAdjacencyListClassName.copy(true)
        )
    ),
    BTBFS(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BTBFS.name,
            name = "bfsTree",
            args = listOf(
                "head" to BinaryNodeIntTypeName,
                "needle" to Int::class.asTypeName()
            ),
            returnType = Boolean::class.asTypeName()
        )
    ),
    BTInOrder(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BTInOrder.name,
            name = "inOrderSearch",
            args = listOf("head" to BinaryNodeIntTypeName),
            returnType = ArrayOfIntsTypeName
        )
    ),
    BTPostOrder(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BTPostOrder.name,
            name = "postOrderSearch",
            args = listOf("head" to BinaryNodeIntTypeName),
            returnType = ArrayOfIntsTypeName
        )
    ),
    BTPreOrder(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.BTPreOrder.name,
            name = "preOrderSearch",
            args = listOf("head" to BinaryNodeIntTypeName),
            returnType = ArrayOfIntsTypeName
        )
    ),
    CompareBinaryTrees(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.CompareBinaryTrees.name,
            name = "compare",
            args = listOf(
                "a" to BinaryNodeIntTypeName.copy(true),
                "b" to BinaryNodeIntTypeName.copy(true)
            ),
            returnType = Boolean::class.asTypeName()
        )
    ),
    DFSOnBST(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.DFSOnBST.name,
            name = "dfsTree",
            args = listOf(
                "head" to BinaryNodeIntTypeName,
                "needle" to Int::class.asTypeName()
            ),
            returnType = Boolean::class.asTypeName()
        )
    ),
    MazeSolver(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.MazeSolver.name,
            name = "solve",
            args = listOf(
                "maze" to List::class.asClassName().parameterizedBy(String::class.asTypeName()),
                "wall" to String::class.asTypeName(),
                "start" to PointClassName,
                "end" to PointClassName,
            ),
            returnType = List::class.asClassName().parameterizedBy(PointClassName)
        )
    ),
    TwoCrystalBalls(
        AlgoFunction(
            fileName = DataStructuresAndAlgorithms.TwoCrystalBalls.name,
            name = "twoCrystalBalls",
            args = listOf("breaks" to List::class.asClassName().parameterizedBy(Boolean::class.asTypeName())),
            returnType = Int::class.asTypeName()
        )
    ),
    Trie(
        DS(
            name = DataStructuresAndAlgorithms.Trie.name,
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Trie.name,
                    name = "insert",
                    args = listOf("item" to String::class.asTypeName())
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Trie.name,
                    name = "delete",
                    args = listOf("item" to String::class.asTypeName())
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Trie.name,
                    name = "find",
                    args = listOf("partial" to String::class.asTypeName()),
                    returnType = Array::class.asClassName().parameterizedBy(String::class.asTypeName())
                )
            )
        )
    ),
    Stack(
        DS(
            name = DataStructuresAndAlgorithms.Stack.name,
            receiverType = ReceiverType(),
            props = Props(fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Stack.name,
                    name = "push",
                    args = listOf("item" to t)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Stack.name,
                    name = "pop",
                    args = listOf(),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Stack.name,
                    name = "peek",
                    args = listOf(),
                    returnType = t.copy(true)
                )
            )
        )
    ),
    SingleLinkedList(
        DS(
            name = DataStructuresAndAlgorithms.SinglyLinkedList.name,
            receiverType = ReceiverType(),
            props = Props(fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "append",
                    args = listOf("item" to t)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "prepend",
                    args = listOf()
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "insertAt",
                    args = listOf(
                        "index" to Int::class.asTypeName(),
                        "item" to t
                    )
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "removeAt",
                    args = listOf("index" to Int::class.asTypeName()),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "remove",
                    args = listOf("item" to t),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "get",
                    args = listOf("index" to Int::class.asTypeName()),
                    returnType = t.copy(true)
                )
            )
        )
    ),
    RingBuffer(
        DS(
            name = DataStructuresAndAlgorithms.RingBuffer.name,
            receiverType = ReceiverType(),
            props = Props(fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.RingBuffer.name,
                    name = "push",
                    args = listOf("item" to t)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.RingBuffer.name,
                    name = "pop",
                    args = listOf(),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.RingBuffer.name,
                    name = "get",
                    args = listOf("index" to Int::class.asTypeName()),
                    returnType = t.copy(true)
                )
            )
        )
    ),
    Queue(
        DS(
            name = DataStructuresAndAlgorithms.Queue.name,
            receiverType = ReceiverType(),
            props = Props(fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Queue.name,
                    name = "enqueue",
                    args = listOf("item" to t)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Queue.name,
                    name = "dequeue",
                    args = listOf(),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Queue.name,
                    name = "peek",
                    args = listOf(),
                    returnType = t.copy(true)
                )
            )
        )
    ),
    MinHeap(
        DS(
            name = DataStructuresAndAlgorithms.MinHeap.name,
            props = Props(fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.MinHeap.name,
                    name = "insert",
                    args = listOf("value" to Int::class.asTypeName())
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.MinHeap.name,
                    name = "delete",
                    args = listOf(),
                    returnType = Int::class.asTypeName()
                )
            )
        )
    ),
    Map(
        DS(
            name = DataStructuresAndAlgorithms.Map.name,
            receiverType = ReceiverType(hasMultiple = true),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Map.name,
                    name = "get",
                    args = listOf("key" to t),
                    returnType = v.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Map.name,
                    name = "set",
                    args = listOf("key" to t, "value" to v)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Map.name,
                    name = "delete",
                    args = listOf("key" to t),
                    returnType = v.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.Map.name,
                    name = "size",
                    args = listOf(),
                    returnType = Int::class.asTypeName()
                )
            )
        )
    ),
    DoublyLinkedList(
        DS(
            name = DataStructuresAndAlgorithms.DoublyLinkedList.name,
            receiverType = ReceiverType(),
            props = Props(fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.DoublyLinkedList.name,
                    name = "append",
                    args = listOf("item" to t)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.DoublyLinkedList.name,
                    name = "prepend",
                    args = listOf("item" to t)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.DoublyLinkedList.name,
                    name = "insertAt",
                    args = listOf(
                        "index" to Int::class.asTypeName(),
                        "item" to t
                    )
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.DoublyLinkedList.name,
                    name = "removeAt",
                    args = listOf("index" to Int::class.asTypeName()),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.DoublyLinkedList.name,
                    name = "remove",
                    args = listOf("item" to t),
                    returnType = t.copy(true)
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.SinglyLinkedList.name,
                    name = "get",
                    args = listOf("index" to Int::class.asTypeName()),
                    returnType = t.copy(true)
                )
            )
        )
    ),
    LRU(
        DS(
            name = DataStructuresAndAlgorithms.LRU.name,
            receiverType = ReceiverType(hasMultiple = true),
            props = Props(primaryConstructor = true, fields = listOf("length" to Int::class.asTypeName())),
            functions = listOf(
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.LRU.name,
                    name = "update",
                    args = listOf("key" to t, "value" to v),
                ),
                AlgoFunction(
                    fileName = DataStructuresAndAlgorithms.LRU.name,
                    name = "get",
                    args = listOf("key" to t),
                    returnType = v.copy(true)
                )
            )
        )
    )
}

val t = TypeVariableName("T")
val v = TypeVariableName("V")
private val BinaryNodeIntTypeName = BinaryNode::class.asClassName().parameterizedBy(Int::class.asTypeName())
private val ArrayOfIntsTypeName = Array::class.asClassName().parameterizedBy(Int::class.asTypeName())
private val WeightedAdjacencyListClassName = ClassName("util", "WeightedAdjacencyList")
private val WeightedAdjacencyMatrixClassName = ClassName("util", "WeightedAdjacencyMatrix")
private val PointClassName = ClassName("util", "Point")
private val sortingArgs = listOf("arr" to (Array::class).asClassName().parameterizedBy(Int::class.asTypeName()))
private val searchingArgs = listOf(
    "haystack" to (Array::class).asClassName().parameterizedBy(Int::class.asTypeName()),
    "needle" to Int::class.asTypeName()
)
private val graphSearchArgs = listOf(
    "graph" to WeightedAdjacencyListClassName,
    "source" to Int::class.asTypeName(),
    "needle" to Int::class.asTypeName()
)
private val graphMatrixArgs = listOf(
    "graph" to WeightedAdjacencyMatrixClassName,
    "source" to Int::class.asTypeName(),
    "needle" to Int::class.asTypeName()
)