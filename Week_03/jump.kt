package chunzhao.Week_03

/**
 * https://leetcode-cn.com/problems/jump-game-ii/
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 */

/**
 * 从当前位置选择 最优的下一步，最优的下一步的条件是 下一步能走最远的距离
 */
fun jump(nums: IntArray): Int {
    // 1. terminate, index + nums[index] >= size

    // 2. process, step ++, index = next

    // 3. recurse

    // 4. reverse state

    if (nums.size == 1) {
        return 0
    }

    var index = 0
    var step = 0

   do {
        index = next(nums, index)
        step++
    } while (index < nums.size - 1)

    return step
}

/**
 * 找可以跳最远的下一步
 */
private fun next(nums: IntArray, index: Int): Int {
    if (index + nums[index] >= nums.size - 1) {
        return nums.size-1
    }

    val to = index + nums[index]

    var max = 0
    var nextIndex = 0

    (index + 1..to).forEach {
        if (it + nums[it] >= max) {
            nextIndex = it
            max = it + nums[it]
        }
    }

    return nextIndex
}


fun jump2(nums: IntArray): Int {
    var endPosition = 0
    var maxPosition = 0
    var steps = 0

    nums.forEachIndexed { index, step ->
        maxPosition = Math.max(maxPosition, index + step)

        if (index == endPosition) {
            endPosition = maxPosition
            steps++
        }
    }

    return steps
}