package chunzhao.Week_03

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * 33. 搜索旋转排序数组
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
 *
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 */

/**
 * 递归 - 二分查找
 * 时间： O(logN) = logN
 * 空间： O(1), 但是 因为每次递归都要存一次调用栈，所以还要 额外消耗 O(logN) 调用栈空间
 */
fun search(nums: IntArray, target: Int): Int {

    fun search(nums: IntArray, target: Int, left: Int, right: Int): Int {
        if (left + 1 >= right) {
            if (nums[left] == target) {
                return left
            } else if (nums[right] == target) {
                return right
            } else {
                return -1
            }
        }
//
//        if (nums[left] == target) return left
//
//        if (nums[right] == target) return right


        val middle = (left + right) / 2

        if (nums[left] < nums[middle]) {
            // left - middle up
            if (nums[left] < target && target <= nums[middle]) {
                return search(nums, target, left, middle)
            } else {
                return search(nums, target, middle, right)
            }
            // middle - right down, up
        } else {
            // middle - right, up
            if (nums[middle] <= target && target <= nums[right]) {
                return search(nums, target, middle, right)
            } else {
                // left - middle, up, down
                return search(nums, target, left, middle)
            }
        }
    }

    return search(nums, target, 0, nums.size - 1)

    // 1. terminator

    // 2. process

    // 3. traversal

    // 4. reverse state
}

/**
 * 迭代-二分搜索
 */
fun search2(nums: IntArray, target: Int): Int {
    var lo = 0
    var hi = nums.size - 1

    /**
     * 判读 target 是否可能在 [from, to] 的 升序序列内
     */
    fun inRanged(nums: IntArray, from: Int, to: Int, target: Int): Boolean {
        return nums[from] <= target && target <= nums[to]
    }

    while (lo < hi) {
        val mid = (lo + hi) / 2

        val isLeftUp = nums[lo] <= nums[mid]

        if (isLeftUp) {
            // 左边升序
            if (inRanged(nums, lo, mid, target)) {
                hi = mid
            } else {
                lo = mid+1
            }
        } else {
            // 右边 升序
            if (inRanged(nums, mid, hi, target)) {
                lo = mid
            } else {
                hi = mid - 1
            }
        }
    }

    if (nums[lo] == target) {
        return lo
    }

    return -1
}