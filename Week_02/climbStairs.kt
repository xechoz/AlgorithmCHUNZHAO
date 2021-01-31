package chunzhao.Week_02

import java.util.concurrent.*
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数
 *
 * f(n) = f(n-1) + f(n-2) = f(n-2) + f(n-3) + f(n-3) + f(n-4)
 */
fun climbStairs(n: Int): Int {
    // terminate
    if (n == 1) {
        return 1
    }

    if (n == 2) {
        return 2
    }

    // process
    return 1* climbStairs(n-1) + 1* climbStairs(n -2 )
    // drill down

    // reverse state
}

/**
 * 加 cache, key = n, v = result
 */
private val map = mutableMapOf<Int, Int>().apply {
    put(1, 1)
    put(2, 3)
    put(3, 4)
    put(4, 7)
}

fun climbStairs2(n: Int): Int {
    if (map.containsKey(n)) {
        return map[n] as Int
    }

    val n2 = climbStairs2(n-2)
    map[n-2] = n2

    val n1 = climbStairs2(n-1)
    map[n-1] = n1
    map[n] = n1 + n2
    return n1 + n2
}


fun climbStairs3(n: Int): Int {
   val result = mutableListOf<Int>()
    result[0] = 0
    result[1] = 1
    result[2] = 2

    (3..n).forEach { i ->
        result[i] = result[i-1] + result[i-2]
    }

    return result[n]
}