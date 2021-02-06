package chunzhao.Week_03

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 贪心策略：如果明天的价格比今天高，今天就买进，明天卖出，让后重复这个买卖逻辑
 * 时间：O(n): 遍历一次数组
 * 空间：O(1):
 */
fun maxProfit(prices: IntArray): Int {
    var sum = 0
    var last = Int.MAX_VALUE // 记录昨天的价格，如果 就在 昨天买入，今天卖出

    prices.forEach {
        // 这个是 变化的部分，可以抽象为 function

        val delta = it - last

        if (delta > 0) {
            // sell
            sum += delta
        }

        last = it
    }

    return sum
}