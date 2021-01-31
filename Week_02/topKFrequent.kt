package chunzhao.Week_02

import java.util.*
import kotlin.collections.LinkedHashMap

/**
 * 1. 统计频率: 用 map 存放每个数字频率， k=num, v = 次数
 * 2. 获取频率最高的前 k 个
 *  2.1 PriorityQueue 或者 自己根据 频率排序
 *
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @see MainTest.topKFrequentTest
 *
 * 使用 sorted map, 根据频次排序的 map
 */
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val frequentMap = mutableMapOf<Int, Int>()

    nums.forEach {
        frequentMap[it] = (frequentMap[it] ?: 0) + 1
    }

    val result = frequentMap.toSortedMap { o1, o2 ->
        (frequentMap[o2] as Int) - (frequentMap[o1] as Int)
    }

    return result.keys.toList().subList(0, k).toIntArray()
}

/**
 * 使用 PriorityQueue 最大堆
 *
 * 时间：O(n*logN) = n + n*logN + k*LogN, 构造 map = O(n), 遍历 map = O(n) * logN, 取前 k = k*logN
 * 空间：O(n) = n + n, map = O(n), heap = O(n)
 */
fun topKFrequent2(nums: IntArray, k: Int): IntArray {
    val frequentMap = mutableMapOf<Int, Int>()

    nums.forEach {
        frequentMap[it] = (frequentMap[it] ?: 0) + 1
    }

    // heap 只保存 key，对应的 频次 可以用 map 直接读取
    val heap = PriorityQueue<Int>(k) { l, r ->
        -(frequentMap[l] as Int - frequentMap[r] as Int)
    }

    frequentMap.forEach {
        heap.add(it.key)
    }

    return (0 until k).map {
        heap.poll()
    }.toIntArray()
}

/**
 * 使用 最小堆，只存 k 个, 小于 root 的不要，大于的才加入，
 * 最后 root 是 第 k 大的，child 都比 root 大
 *
 * 时间： O(n) = n + n * logK + k = N*logN， map = n, 遍历 map & 构造 heap = n*logK, 遍历 heap = k
 * 空间： O(n) = n + k = O(n), map = O(n), heap = O(k)
 */
fun topKFrequent3(nums: IntArray, k: Int): IntArray {
    val frequentMap = LinkedHashMap<Int, Int>(64)

    nums.forEach {
        frequentMap[it] = (frequentMap[it] ?: 0) + 1
    }

    // heap 只保存 key，对应的 频次 可以用 map 直接读取
    val heap = PriorityQueue<Int>(k) { l, r ->
        // 跟最大堆相反，这里不要取反
        (frequentMap[l] as Int - frequentMap[r] as Int)
    }

    frequentMap.forEach {
        if (heap.size < k) {
            // 构造大小为 k 的 heap
            heap.add(it.key)
        } else {
            // 只添加 比 top 大的
            val top = frequentMap[heap.peek()] as Int

            if (it.value > top) {
                heap.poll()
                heap.add(it.key)
            }
        }
    }

    return heap.toList().toIntArray()
}