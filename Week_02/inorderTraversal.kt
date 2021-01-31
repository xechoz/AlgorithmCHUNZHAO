package chunzhao.Week_02

import chunzhao.common.TreeNode
import java.util.*
import kotlin.collections.ArrayList

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
private val result = ArrayList<Int>(1024) // 预加载

fun inorderTraversal(root: TreeNode?): List<Int> {
    // 1
//    // terminate
//    if (root == null) {
//        return emptyList()
//    }
//
//    // process
//    // left
//    return inorderTraversal(root.left) + listOf(root.`val`) + inorderTraversal(root.right)
//    // root
//
//    // right
//
//
//    // traversal
//
//    // clean

    //

    // 2

//    if (root == null) {
//        return emptyList()
//    }
//
//    inorderTraversal(root.left)
//    result.add(root.`val`)
//    inorderTraversal(root.right)
//
//    return result

    // 3

    val stack = Stack<TreeNode>()
    var node = root
    val result = mutableListOf<Int>()

    while (node != null || stack.isNotEmpty()) {
        while (node != null) {
            stack.push(node)
            node = node.left
        }

        node = stack.pop()
        result.add(node.`val`)
        node = node.right
    }

    return result
}
