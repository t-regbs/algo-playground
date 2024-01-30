package util
data class CompleteGraphEdge(val from: Int, val to: Int, val weight: Int)
data class GraphEdge(val to: Int, val weight: Int)

typealias WeightedAdjacencyList = Array<Array<GraphEdge>>

typealias WeightedAdjacencyMatrix = Array<Array<Int>> // Int means weight

typealias AdjacencyList = Array<Array<Int>>

typealias AdjacencyMatrix = Array<Array<Int>> // A 1 means connected

interface ILRU<K, V> {

    fun update(key: K, value: V)

    fun get(key: K): V?

}

data class Point(val x: Int, val y: Int)

enum class DataStructuresAndAlgorithms {
    BFSGraphList,
    BFSGraphMatrix,
    DFSGraphList,
    DijkstraList,
    PrimsList,
    BinarySearch,
    LinearSearch,
    BubbleSort,
    InsertionSort,
    MergeSort,
    QuickSort,
    BTBFS,
    BTInOrder,
    BTPostOrder,
    BTPreOrder,
    CompareBinaryTrees,
    DFSOnBST,
    MazeSolver,
    TwoCrystalBalls,
    DoublyLinkedList,
    LRU,
    Map,
    MinHeap,
    Queue,
    RingBuffer,
    SinglyLinkedList,
    Stack,
    Trie
}
