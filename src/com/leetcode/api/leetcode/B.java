package com.leetcode.api.leetcode;

/**
 * 有道领世
 * leetcode
 * Description:
 * Created by wangzheng on 2023/12/21 10:29
 * Copyright @ 2023 网易有道. All rights reserved.
 **/
public class B extends A{
    private String s = "sf";
    @Override
    String getP() {
        return s;
    }
    B(){
        System.out.println("B 构造函数" + getP());
    }
}


