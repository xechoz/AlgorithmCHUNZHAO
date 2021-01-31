package chunzhao.common

/**
 *  这个类存放 公共的数据结构，比如树的节点 Node
 */


/**
 * N 叉 树节点
 */
class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

/**
 * 二 叉树节点
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
