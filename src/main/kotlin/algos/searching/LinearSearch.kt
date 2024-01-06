package algos.searching

fun linearSearch(haystack: Array<Int>, needle: Int): Boolean {
    for (i in haystack) {
        if (i == needle) {
            return true
        }
    }
    return false
}