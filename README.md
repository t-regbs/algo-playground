# Algo Playground

Tech Demo that has become a real thing :)

Repo for daily DSA practice in kotlin. Inspired by this [katas](https://github.com/ThePrimeagen/kata-machine)

### Supported Data Structures
- [x] Map
- [x] Trie
- [x] Stack
- [x] Queue
- [x] SinglyLinkedList
- [x] DoublyLinkedList
- [x] RingBuffer
- [x] MinHeap
- [x] LRU

### Supported Algorithms
- [x] InsertionSort
- [x] MergeSort
- [x] QuickSort
- [x] BubbleSort
- [x] BinarySearch
- [x] LinearSearch
- [x] BTBFS (BFS on Binary Tree)
- [x] BTInOrder (In Order Traversal of Binary Tree)
- [x] BTPostOrder (Post Order Traversal of Binary Tree)
- [x] BTPreOrder (Pre Order Traversal of Binary Tree)
- [x] CompareBinaryTrees
- [x] DFSOnBST (DFS on Binary Tree)
- [x] CompareBinaryTrees
- [x] PrimsList (Prim's MST - Adjacency List)
- [x] DijkstraList (Dijkstra's Shortest Path -Adjacency List)

### How to use it

1. Set currentDay in your local `gradle.properties` file like so:

`currentDay=1`

2. Add the short name for the algos you want to practice on that day to `algoList` in the `build.gradle.kts` file:

```kotlin
val algoList = listOf(
    "Map",
    "InsertionSort",
    "PrimsList",
    "BFSGraphList",
    "LinearSearch",
    "Trie",
    "Stack",
    "BTInOrder",
)
```

3. Generate skeleton code for the algos in `algoList` by running:

`gradlew runGenerator`

4. Write implementations for the algos

5. Verify your implementations by running:

`gradlew runTests`

### TODOS:
- [ ] Add mechanism to autoincrement current day
- [ ] Add gradle task to run single test
- [ ] Add gradle task to clear day
- [ ] Add gradle task to clear all days
- [ ] Add progress tracking / reporting
- [ ] Add more algos
- [ ] Add more tests

