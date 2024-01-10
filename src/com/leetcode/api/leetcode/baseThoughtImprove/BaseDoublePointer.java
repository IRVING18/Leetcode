package com.leetcode.api.leetcode.baseThoughtImprove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 双指针
 */
public class BaseDoublePointer {
    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len; i ++) {
            //去重
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int a = nums[i];
            int s = - a;
            int leftI = i + 1;
            int rightI = len - 1;
            while(leftI < rightI) {
                int sum = nums[leftI] + nums[rightI];

                if(sum > s) {
                    rightI --;
                } else if(sum < s) {
                    leftI ++;
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[leftI]);
                    list.add(nums[rightI]);
                    ans.add(list);

                    int left = nums[leftI];
                    int right = nums[rightI];

                    //去重
                    while(leftI < rightI && nums[leftI] == left) {
                        leftI ++;
                    }
                    while(leftI < rightI && nums[rightI] == right) {
                        rightI --;
                    }
                }
            }
        }
        return ans;
    }
}
