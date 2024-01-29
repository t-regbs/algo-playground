package scripts

import scripts.TestType.*
import util.DataStructuresAndAlgorithms

enum class AlgoTests(
    val test: Test
) {
    BubbleSort(
        Test(name = "${DataStructuresAndAlgorithms.BubbleSort.name}Test", type = Sorting)
    ),
    InsertionSort(
        Test(name = "${DataStructuresAndAlgorithms.InsertionSort.name}Test", type = Sorting)
    ),
    MergeSort(
        Test(name = "${DataStructuresAndAlgorithms.MergeSort.name}Test", type = Sorting)
    ),
    QuickSort(
        Test(name = "${DataStructuresAndAlgorithms.QuickSort.name}Test", type = Sorting)
    ),
    BinarySearch(
        Test(name = "${DataStructuresAndAlgorithms.BinarySearch.name}Test", type = Searching)
    ),
    LinearSearch(
        Test(name = "${DataStructuresAndAlgorithms.LinearSearch.name}Test", type = Searching)
    ),
    BFSGraphList(
        Test(name = "${DataStructuresAndAlgorithms.BFSGraphList.name}Test", type = Graph)
    ),
    BFSGraphMatrix(
        Test(name = "${DataStructuresAndAlgorithms.BFSGraphMatrix.name}Test", type = Graph)
    ),
    DFSGraphList(
        Test(name = "${DataStructuresAndAlgorithms.DFSGraphList.name}Test", type = Graph)
    ),
    DijkstraList(
        Test(name = "${DataStructuresAndAlgorithms.DijkstraList.name}Test", type = Graph)
    ),
    PrimsList(
        Test(name = "${DataStructuresAndAlgorithms.PrimsList.name}Test", type = Graph)
    ),
    BTBFS(
        Test(name = "${DataStructuresAndAlgorithms.BTBFS.name}Test", type = Tree)
    ),
    BTInOrder(
        Test(name = "${DataStructuresAndAlgorithms.BTInOrder.name}Test", type = Tree)
    ),
    BTPostOrder(
        Test(name = "${DataStructuresAndAlgorithms.BTPostOrder.name}Test", type = Tree)
    ),
    BTPreOrder(
        Test(name = "${DataStructuresAndAlgorithms.BTPreOrder.name}Test", type = Tree)
    ),
    CompareBinaryTrees(
        Test(name = "${DataStructuresAndAlgorithms.CompareBinaryTrees.name}Test", type = Tree)
    ),
    DFSOnBST(
        Test(name = "${DataStructuresAndAlgorithms.DFSOnBST.name}Test", type = Tree)
    ),
    MazeSolver(
        Test(name = "${DataStructuresAndAlgorithms.MazeSolver.name}Test", type = Other)
    ),
    TwoCrystalBalls(
        Test(name = "${DataStructuresAndAlgorithms.TwoCrystalBalls.name}Test", type = Other)
    ),
    Trie(
        Test(name = "${DataStructuresAndAlgorithms.Trie.name}Test", type = DS)
    ),
    Stack(
        Test(name = "${DataStructuresAndAlgorithms.Stack.name}Test", type = DS)
    ),
    SingleLinkedList(
        Test(name = "${DataStructuresAndAlgorithms.SinglyLinkedList.name}Test", type = DS)
    ),
    RingBuffer(
        Test(name = "${DataStructuresAndAlgorithms.RingBuffer.name}Test", type = DS)
    ),
    Queue(
        Test(name = "${DataStructuresAndAlgorithms.Queue.name}Test", type = DS)
    ),
    MinHeap(
        Test(name = "${DataStructuresAndAlgorithms.MinHeap.name}Test", type = DS)
    ),
    Map(
        Test(name = "${DataStructuresAndAlgorithms.Map.name}Test", type = DS)
    ),
    DoublyLinkedList(
        Test(name = "${DataStructuresAndAlgorithms.DoublyLinkedList.name}Test", type = DS)
    ),
    LRU(
        Test(name = "${DataStructuresAndAlgorithms.LRU.name}Test", type = DS)
    )
}