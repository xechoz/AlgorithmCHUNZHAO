import java.util.ArrayList

/**
 * 因为大部分雪花都是不一样的，所以可以为每个雪花 生成一个 hash, hash 值不同的一定不一样，这样可以大幅减少计算量
 *
 * if (hash(a) != hash(b)) {
 * // 一定不同
 * } else {
 * // 可能相同
 * // 精确匹配
 * }
 * @param snows
 *
 * @return "Yes" or "No"
 */
fun findSameSnow(snows: Array<IntArray>): String? {
    /**
     * 放 key 以及 对于的 int[]
     */
    val map = HashMap<Int, MutableList<IntArray>>(snows.size / 2)
    for (i in snows) {
        val key = key(i)
        if (map.containsKey(key)) {
            if (isSame(map[key]!!, i)) {
                return "Yes"
            } else {
                map[key]!!.add(i)
            }
        } else {
            val list = ArrayList<IntArray>()
            list.add(i)
            map[key] = list
        }
    }
    return "No"
}

private fun key(a: IntArray): Int {
    var orSum = 0 //
    var andSum = 1
    for (i in a) {
        orSum = orSum or i
        andSum = andSum and i + 1 // 避免 0
    }
    return orSum and andSum
}

private fun isSame(list: List<IntArray>, a: IntArray): Boolean {
    for (i in list) {
        if (isSame(i, a)) {
            return true
        }
    }
    return false
}

private fun isSame(a: IntArray, b: IntArray): Boolean {
    for (i in 0..5) {
        if (isSame(a, b, i, false) || isSame(a, b, i, true)) {
            return true
        }
    }
    return false
}

private fun isSame(a: IntArray, b: IntArray, start: Int, reverse: Boolean): Boolean {
    return if (reverse) {
        for (i in a.indices) {
            if (a[(i + start) % a.size] != b[b.size - 1 - i]) {
                return false
            }
        }
        true
    } else {
        for (i in a.indices) {
            if (a[(i + start) % a.size] != b[i]) {
                return false
            }
        }
        true
    }
}