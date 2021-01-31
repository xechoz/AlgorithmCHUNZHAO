package chunzhao.Week_02

import chunzhao.common.TreeNode
import java.util.*
import kotlin.collections.ArrayList

//private val list = mutableListOf<Int>()
/**
 * 根据可能的数据集大小，提前分配数组，用内存换速度
 */
private val list = ArrayList<Int>(64)

/**
 * 递归
 *
 * 时间复杂度：每个node 只遍历一次，所以是 O(n)
 * 空间复杂度：用数组存放结果, 只要存放每个节点，是 O(n) = k*n， k 是 ArrayList 处罚 扩容&预加载 消耗的空间，k in  [1.0, 1.5]
 * 另外，递归的调用栈也有消耗，每个非空的 node 都要消耗一次，所以也有 O(n) 的调用栈空间消耗
 */

// root, left, right
//fun preorderTraversal(root: TreeNode?): List<Int> {
//    // 1. terminal
//    if (root == null) {
//        return emptyList()
//    }
//
//    // 2. process
//    // add node
//    list.add(root.`val`)
//
//    // 3. traveral, add left, add right
//
//    preorderTraversal(root.left)
//    preorderTraversal(root.right)
//
//    // 4. clean
//
//    return list
//}

/**
 * 2. 迭代
 *
 * 迭代跟递归类似，也是每个 node 遍历一次，只是用 一个 stack 替换了 程序调用栈的消耗
 *
 * 时间复杂度：O(n)
 * 每个 Node 遍历一次，执行 push & pop 以及 其他判断，是 O(n) = kn , k in [2, m], m 是 while 循环里除了 push&pop 之外的判断
 * 空间： O(n) = n + n/2
 * 用一个 List 存放结果 O(n)， 另外加上 Stack 存放中途的 node，每个 while 循环，最坏情况 每次是 pop, push left, push right，所以需要 O(n) = n/2
 */
fun preorderTraversal(root: TreeNode?): List<Int> {
    // 1. terminal
    if (root == null) {
        return emptyList()
    }


    /**
     * 根据可能的数据集大小，提前分配数组，用内存换速度
     */
    val resultList = ArrayList<Int>(64)

    // 存放下一步的 node
    val stack = Stack<TreeNode>()
    stack.push(root)

    // root, left, right
    while (stack.isNotEmpty()) {
        // root

        // 2. process
        // add node
        val node = stack.pop()

        resultList.add(node.`val`)

        // 3. traveral, add left, add right

        // fist in, last out
        node.right?.let {
            stack.push(it)
        }

        // todo: 这里 push 了，等一下循环又 pop，可以优化，避免多余的 push&pop
        // last in, first out
        node.left?.let {
            stack.push(it)
        }
    }

    // 4. clean。 do nothing
    return resultList
}
