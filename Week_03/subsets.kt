package chunzhao.Week_03

/**
 * https://leetcode-cn.com/problems/subsets/
 *
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集
 * 时间：O(2^n): f(n) = 2*f(n-1)
 * 空间:
 */
fun subsets(nums: IntArray): List<List<Int>> {
    // 1. terminate, all iterated

    // 2. process, not in list and not large then expect size, add it

    // 3. traversal

    // 4. reverse state

    val result = mutableListOf<List<Int>>()
    val ans = mutableListOf<Int>()

    fun dfs(index: Int, nums: IntArray) {
        if (index == nums.size) {
            result.add(ans.toList())
            return
        }

        ans.add(nums[index])
        dfs(index + 1, nums)
        ans.removeLast()
        dfs(index + 1, nums)
    }

    dfs(0, nums)
    return result
}