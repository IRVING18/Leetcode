package com.leetcode.api.leetcode.baseThoughtImprove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode
 * Description: 滑动窗口
 * Created by wangzheng on 2024/1/12 11:38
 * Copyright @ 2024 网易有道. All rights reserved.
 **/
class BaseSlidingWindow {

    /**
     * 30. 串联所有单词的子串
     * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
     *  s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
     * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
     * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
     *
     * 示例 1：
     *
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
     * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
     * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
     * 输出顺序无关紧要。返回 [9,0] 也是可以的。
     *
     *
     * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/?envType=study-plan-v2&envId=top-interview-150
     */
    public List<Integer> findSubstring(String s, String[] words) {
        //1、粗暴理解滑动窗口：用例可过，但是时间有点长
//        List<Integer> res = new ArrayList<Integer>();
//        if(words.length < 1) {
//            return res;
//        }
//        int step = words[0].length() * words.length;
//        int start = 0;
//        int end = start + step;
//
//        while(end <= s.length()) {
//            String sub = s.substring(start, end);
//            if(isChild(sub, words)) {
//                res.add(start);
//            }
//            start ++;
//            end ++;
//        }
//        return res;

        List<Integer> res = new ArrayList<>();
        // 所有单词的个数
        int num = words.length;
        // 每个单词的长度（是相同的）
        int wordLen = words[0].length();
        // 字符串长度
        int stringLen = s.length();

        for (int i = 0; i < wordLen; i++) {
            // 遍历的长度超过了整个字符串的长度，退出循环
            if (i + num * wordLen > stringLen) {
                break;
            }
            // differ表示窗口中的单词频次和words中的单词频次之差
            Map<String, Integer> differ = new HashMap<>();
            // 初始化窗口，窗口长度为num * wordLen,依次计算窗口里每个切分的单词的频次
            for (int j = 0; j < num; j++) {
                String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            // 遍历words中的word，对窗口里每个单词计算差值
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                // 差值为0时，移除掉这个word
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            // 开始滑动窗口
            for (int start = i; start < stringLen - num * wordLen + 1; start += wordLen) {
                if (start != i) {
                    // 右边的单词滑进来
                    String word = s.substring(start + (num - 1) * wordLen, start + num * wordLen);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    // 左边的单词滑出去
                    word = s.substring(start - wordLen, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - wordLen, start);
                }
                // 窗口匹配的单词数等于words中对应的单词数
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

    boolean isChild(String s, String[] words) {
        Map<String, Integer> map = new HashMap();
        for(int i = words.length - 1; i >= 0; i --) {
            if(map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }
        }

        int step = words[0].length();
        int start = 0;
        int end = start + step;

        while(end <= s.length()) {
            String sub = s.substring(start, end);
            if(map.containsKey(sub)) {
                int count = map.get(sub) - 1;
                if(count == 0) {
                    map.remove(sub);
                } else {
                    map.put(sub, count);
                }
            } else {
                return false;
            }
            start += step;
            end += step;
        }

        return true;

    }
}
