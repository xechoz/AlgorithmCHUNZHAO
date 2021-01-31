package chunzhao.Week_02

import chunzhao.common.Node
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var children: List<Node?> = listOf()
 * }
 *
 * 429. N 叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 */

/**
 * BFS 模板代码
 */

fun bfs(root: Node) {
    // 1. queue
    val queue = ArrayDeque<Node>()
    queue.add(root)
    val result = mutableListOf<Node>()

    // 2. traversal
    while (queue.isNotEmpty()) {
       val node = queue.removeFirst()
        result.add(node)

        // 3. push to end
        queue.addAll(node.children.filterNotNull())
    }
}

/**
 * 时间：O(n) = 2n = N, 因为 每层的 child 都是 先 add, 再 remove, 所以是 2*N
 * 空间：O(n) = n, 只保存了全部 node
 */
fun levelOrder(root: Node?): List<List<Int>> {
    // 边界条件
    if (root == null) {
        return emptyList()
    }

    val result = mutableListOf<List<Int>>()

    // queue
    // 存放，下一 level 的 node list
    val queue = ArrayDeque<Node>()
    queue.add(root)

    // 2. traversal
    while (queue.isNotEmpty()) {
        // 存放 每层 level node list
        val level = mutableListOf<Int>()

        // 遍历当前 level，这里把 size 取出来了，所以不受下面的 queue.addAll 影响
        (0 until queue.size).forEach {
            val node = queue.removeFirst()
            level.add(node.`val`)

            // 下一 level 的 node list
            if (node.children.isNotEmpty()) {
                queue.addAll(node.children.filterNotNull())
            }
        }

        // 保存结果
        result.add(level)
    }

    return result
}