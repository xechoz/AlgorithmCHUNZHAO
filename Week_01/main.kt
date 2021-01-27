import java.util.*

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
fun removeDuplicates(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }

    var lastIndex = 0 // 记录填入的index

    nums.forEach {
        // 把下一个不一样的数字，填到 index
        // 的可以不处理，因为题目没有要求置空
        if (it != nums[lastIndex]) {
            lastIndex++
            nums[lastIndex] = it
        }
    }

    return lastIndex + 1
}

/**
 * @see https://leetcode-cn.com/problems/rotate-array/
 */
fun rotate(nums: IntArray, k: Int): Unit {
    // 把后面 k 个 copy 出来
    val temp = nums.copyOfRange(nums.size - k, nums.size)

    // 从后网前赋值 nums[i] = nums[i-k]
    (nums.size - 1 downTo 0).forEach {
        if (it - k >= 0) {
            // size - 1 到 k 在 nums 中获取
            nums[it] = nums[it - k]
        } else {
            // 倒数 k-1 到 0 ，在 temp 里
            nums[it] = temp[it]
        }
    }
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
        return l2
    }

    if (l2 == null) {
        return l1
    }

    if (l1.`val` > l2.`val`) {
        l2.next = mergeTwoLists(l1, l2.next)
        return l2
    } else {
        l1.next = mergeTwoLists(l1.next, l2)
        return l1
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun twoSum(nums: IntArray, target: Int): IntArray {
//    val map = mutableMapOf<Int, MutableSet<Int>>()

//    nums.forEachIndexed { index, i ->
//        map.getOrPut(i) {
//            mutableSetOf()
//        }.add(index)
//    }
//
//    nums.forEachIndexed { index, i ->
//        map[target - i]?.let { indexSet ->
//            indexSet.firstOrNull { it > index }?.let {
//                return intArrayOf(index, it)
//            }
//        }
//    }

    val map = mutableMapOf<Int, Int>()

    nums.forEachIndexed { index, i ->
        if (target - i in map) {
            return intArrayOf(map[target - i]!!, index)
        }

        map[i] = index
    }

    return intArrayOf()
}

fun plusOne(digits: IntArray): IntArray {
    //     tailrec fun plus(from: IntArray, index: Int): IntArray {
//        return if (index <= 0 || from[index] + 1 < 10) {
//            // end
//            if (from[index] + 1 < 10) {
//                from[index] = from[index] + 1
//                from
//            } else {
//                from[index] = 0
//                intArrayOf(1) + from
//            }
//        } else {
//            from[index] = 0
//            plus(from, index - 1)
//        }
//    }
    tailrec fun plus(from: IntArray, index: Int): IntArray {
        val new = from[index] + 1

        return if (new < 10) {
            // end
            from[index] = new
            from
        } else {
            // next
            from[index] = 0

            if (index <= 0) {
                intArrayOf(1) + from
            } else {
                plus(from, index - 1)
            }
        }
    }

    return plus(digits, digits.size - 1)
}

class MyCircularDeque(private val k: Int) {
    private var size = 0
    private var head: Node? = null
    private var tail: Node? = null

    class Node(var value: Int, var next: Node? = null, var previous: Node? = null)

    /** Initialize your data structure here. Set the size of the deque to be k. */


    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    fun insertFront(value: Int): Boolean {
        if (size < k) {
            val node = Node(value, head)
            head?.previous = node
            head = node

            if (tail == null) {
                tail = node
            }

            size++
            return true
        }

        return false
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    fun insertLast(value: Int): Boolean {
        if (size < k) {
            val node = Node(value, null, tail)
            tail?.next = node
            tail = node

            if (head == null) {
                head = node
            }

            size++
            return true
        }

        return false
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    fun deleteFront(): Boolean {
        if (head != null) {
            head = head?.next
            head?.previous = null

            if (head == null) {
                tail = null
            }

            size--
            return true
        }

        return false
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    fun deleteLast(): Boolean {
        if (tail != null) {
            tail = tail?.previous
            tail?.next = null

            if (tail == null) {
                head = null
            }

            size--
            return true
        }

        return false
    }

    /** Get the front item from the deque. */
    fun getFront() = head?.value ?: -1

    /** Get the last item from the deque. */
    fun getRear() = tail?.value ?: -1

    /** Checks whether the circular deque is empty or not. */
    fun isEmpty() = size == 0

    /** Checks whether the circular deque is full or not. */
    fun isFull() = size == k
}
//
///**
// * 用 add first 或 add last 这套新的 API 改写 Deque 的代码。
// *
// * 题目的意思是，实现一个 Deque, 提供 addFirst, addLast 两个接口？
// */
//class Deque<E>(private val list: ArrayList<E> = ArrayList()) : Queue<E>, Collection<E> by list {
//    fun addFirst(e: E) {
//        list.add(0, e)
//    }
//
//    fun addLast(e: E) {
//        list.add(e)
//    }
//
//
//    override fun iterator(): Iterator<E> {
//       return list.iterator()
//    }
//
//    override fun add(element: E): Boolean {
//        return list.add(element)
//    }
//
//    override fun remove(): E {
//        return list.removeFirst()
//    }
//
//    override fun offer(e: E): Boolean {
//       return list.add(e)
//    }
//
//    override fun poll(): E {
//       return list.removeFirst()
//    }
//
//    override fun element(): E {
//        return list.first()
//    }
//
//    override fun peek(): E {
//       return list.last()
//    }
//
//    override fun addAll(elements: Collection<E>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun clear() {
//        TODO("Not yet implemented")
//    }
//
//    override fun remove(element: E): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun removeAll(elements: Collection<E>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun retainAll(elements: Collection<E>): Boolean {
//        TODO("Not yet implemented")
//    }
//}
/**
 * 分析 Queue 和 Priority Queue 的源码。
 */