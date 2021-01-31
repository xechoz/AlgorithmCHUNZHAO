package chunzhao.Week_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindSameSnow {
    /**
     * 因为大部分雪花都是不一样的，所以可以为每个雪花 生成一个 hash, hash 值不同的一定不一样，这样可以大幅减少计算量
     *
     * if (hash(a) != hash(b)) {
     *     // 一定不同
     * } else {
     *     // 可能相同
     *     // 精确匹配
     * }
     * @param snows
     *
     * @return "Yes" or "No"
     */
    public String findSameSnow(int[][] snows) {
        /**
         * 放 key 以及 对于的 int[]
         */
        HashMap<Integer, List<int[]>> map = new HashMap<>(snows.length / 2);

        for (int[] i : snows) {
            int key = key(i);

            if (map.containsKey(key)) {
                if (isSame(map.get(key), i)) {
                    return "Yes";
                } else  {
                    map.get(key).add(i);
                }
            } else {
                ArrayList<int[]> list = new ArrayList<>();
                list.add(i);
                map.put(key, list);
            }
        }

        return "No";
    }

    private int key(int[] a) {
        int orSum = 0; //
        int andSum = 1;

        for (int i: a) {
            orSum |= i;
            andSum &= (i+1); // 避免 0
        }

        return orSum & andSum;
    }

    private boolean isSame(List<int[]> list, int[] a) {
        for (int[] i: list) {
            if (isSame(i, a)) {
                return true;
            }
        }

        return false;
    }

   private boolean isSame(int[] a, int[] b) {
        for (int i=0; i<6; i++) {
            if (isSame(a, b, i, false) || isSame(a, b, i, true)) {
                return true;
            }
        }

        return false;
    }

   private boolean isSame(int[] a, int[] b, int start, boolean reverse) {
        if (reverse) {
            for (int i = 0; i < a.length; i++) {
                if (a[(i + start) % a.length] != b[b.length - 1 - i]) {
                    return false;
                }
            }

            return true;
        } else {
            for (int i = 0; i < a.length; i++) {
                if (a[(i + start) % a.length] != b[i]) {
                    return false;
                }
            }

            return true;
        }
    }
}
