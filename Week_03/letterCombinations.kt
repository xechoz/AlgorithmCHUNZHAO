package chunzhao.Week_03

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */

private val map = mapOf(
    '2' to "abc",
    '3' to "def",
    '4' to "ghi",
    '5' to "jkl",
    '6' to "mno",
    '7' to "pqrs",
    '8' to "tuv",
    '9' to "wxyz"
)

fun letterCombinations(digits: String): List<String> {
    // 1. terminate

    // 2. process

    // 3. recursive

    if (digits.isEmpty()) {
        return emptyList()
    }

    /**
     * root - left - right
     */
    val ans = mutableListOf<Char>()

    val result = mutableListOf<String>()

    fun dfs(index: Int, digits: String) {
        if (index == digits.length) {
            // add result
            result.add(ans.joinToString(""))
            return
        }

        val path = map[digits[index]]
        path!!.forEach {
            ans.add(it)
            dfs(index + 1, digits)
            ans.removeAt(ans.size - 1)
        }
    }

    dfs(0, digits)

    return result
}

