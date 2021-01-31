package chunzhao.Week_02

import java.beans.beancontext.BeanContextChild

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * https://leetcode-cn.com/problems/permutations/
 *
 * 参考 https://leetcode-cn.com/problems/permutations/solution/pei-yang-chou-xiang-neng-li-de-yi-dao-ti-1731/
 */
//
//fun <T, M> backtrack(result: MutableList<List<T>>, 路径 path: List<T>, 选择列表 selector: List<M>) {
//    // 1. terminate
//    if (结束) {
//        result.add(path) // copy path
//        return
//    } else {
//        // 2. traversal
//        选择列表
//        selector.forEach {
//            做选择
//            add to path
//            backtrack(result, path, 选择列表)
//            撤销选择
//            remove from path
//        }
//    }
//}

/**
 * 时间：O() =
 * 空间：
 */
fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val pathList = mutableListOf<Int>()
    backtrack(result, pathList, nums)
    return result
}

fun backtrack(result: MutableList<List<Int>>, pathList: MutableList<Int>, selector: IntArray) {
    // 1. terminal
    if (pathList.size == selector.size) {
        result.add(pathList.toList()) // copy
        return
    } else {
        // 2. traversal
        selector.forEach {
            if (!pathList.contains(it)) {
                // 选择
                pathList.add(it) // 这里加到最后
                backtrack(result, pathList, selector)
                // 撤销，
                pathList.removeLast()
            }
        }
    }
}
