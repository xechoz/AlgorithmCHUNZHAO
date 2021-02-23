package chunzhao.Week_04

/**
 * https://leetcode-cn.com/problems/decode-ways/
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

fun numDecodings(s: String): Int {
    fun isValid(s: String, from: Int, to: Int): Boolean {
        return if (from < 0 || to > s.length) {
            false
        } else {
            if (to - from == 1) {
                val digit = s[from] - '0'
                1 <= digit && digit <= 9
            } else if (to - from == 2) {
                val digit = s[from] - '0'
                val digitB = s[from+1] - '0'

                (digit == 1 && digitB >= 0 && digitB <= 9) || (digit == 2 && digitB >= 0 && digitB <= 6)
            } else false
        }
    }

    // dp at least has two elements
    val dp = IntArray(s.length+2) // dp[i] is the num of decodes of s[0, i]
    dp[0] = if (isValid(s, 0, 1)) 1 else 0
    dp[1] = (if (isValid(s, 0, 2)) 1 else 0) + if (isValid(s, 0, 1) && isValid(s, 1, 2)) 1 else 0
    // 2. recursive dp function
//    f(n, s) = f(n - 1, s, s[index, index+1]) + f(n - 2, s, s[index, index+2])

    (2 until s.length).forEach { index ->
       val dp_n_1 =  if (isValid(s, index, index+1)) {
            dp[index-1]
        } else 0

        val dp_n_2 = if (isValid(s, index - 1, index+1)) {
            dp[index-2]
        } else {
            0
        }

        dp[index] = dp_n_1 + dp_n_2
    }
    return if (s.isNotEmpty()) dp[s.length - 1] else 0
}