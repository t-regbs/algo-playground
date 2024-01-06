package ds

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class TrieTest {
    @Test
    fun testTrie() {

        val trie = Trie()

        trie.insert("foo")
        trie.insert("fool")
        trie.insert("foolish")
        trie.insert("bar")

        assertContentEquals(arrayOf("foo", "fool", "foolish").sorted(), trie.find("fo").sorted())

        trie.delete("fool")

        assertContentEquals(arrayOf("foo", "foolish").sorted(), trie.find("fo").sorted())
    }

}