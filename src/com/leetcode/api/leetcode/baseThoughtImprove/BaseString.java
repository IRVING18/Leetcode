package com.leetcode.api.leetcode.baseThoughtImprove;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串操作
 */
public class BaseString {
    /**
     * 125. 验证回文串
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * 字母和数字都属于字母数字字符。
     * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     *
     *
     * 记住API：
     * 1、Character.toLowerCase()
     * 2、Character.isLetterOrDigit()
     * 3、String s; s.toLowerCase()
     * 4、s.toCharArray();
     */
    public boolean isPalindrome(String s) {
        char[] array = s.toLowerCase().toCharArray();
        int sI = 0;
        int eI = array.length - 1;
        while(sI <= eI) {
            char sC = array[sI];
            char eC = array[eI];

            if(!Character.isLetterOrDigit(sC)) {
                sI ++;
                continue;
            }

            if(!Character.isLetterOrDigit(eC)) {
                eI --;
                continue;
            }

            if(Character.toLowerCase(sC) != Character.toLowerCase(eC)) {
                return false;
            }
            sI ++;
            eI --;
        }
        return true;
    }
    /**
     * 13. 罗马数字转整数
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     */
    Map<Character, Integer> map = new HashMap() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public int romanToInt(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int v = map.get(s.charAt(i));
            if (i < s.length() - 1 && v < map.get(s.charAt(i + 1))) {
                res -= v;
            } else {
                res += v;
            }
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 1) {
            return "";
        }
        String first = strs[0];

        int len = strs.length;
        for (int i = 1; i < len; i++) {
            String cur = strs[i];

            if (!cur.startsWith(first)) {
                if (first.length() == 1) {
                    return "";
                }
                first = first.substring(0, first.length() - 1);
                i = 0;
            }
        }
        return first;
    }

    /**
     * 剑指 Offer 05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/?envType=study-plan&id=lcof&plan=lcof&plan_progress=cafihze
     */
    public String replaceSpace(String s) {
        char[] arr  = new char[s.length() * 3];
        int    size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                arr[size++] = '%';
                arr[size++] = '2';
                arr[size++] = '0';
            } else {
                arr[size++] = c;
            }
        }
        return new String(arr, 0, size);
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
     */
    public static String reverseLeftWords(String s, int n) {
        int nReal = n % s.length();
        if (nReal == 0) {
            return s;
        }
        char[] arr = new char[s.length()];
        for (int i = nReal; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        for (int i = 0; i < nReal; i++) {
            arr[i] = s.charAt(i);
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        reverseLeftWords("adcdfg", 2);
    }

}
