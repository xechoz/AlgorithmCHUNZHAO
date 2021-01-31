package chunzhao.Week_02

import chunzhao.common.TreeNode
import kotlin.math.min

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */

/**
 * BFS
 */

fun minDepth(root: TreeNode?): Int {
    // terminate
    if (root == null) {
        return 0
    }

    // process
    val left = minDepth(root.left)
    val right = minDepth(root.right)

    // traversal
    return if (left > 0 && right > 0) 1 + Math.min(left, right) else {
        if (left == 0) {
            1 + right
        } else {
            1 + left
        }
    }
}

fun maxDepth(root: TreeNode?): Int {
    // terminate
    return if (root == null) {
        0
    } else {
        // process
        +1

        // traversal
        val left = maxDepth(root.left)
        val right = maxDepth(root.right)

        return 1 + Math.max(left, right)
        // revert state
        0
    }
}