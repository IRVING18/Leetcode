package com.leetcode.api.leetcode;

/**
 * 有道领世
 * leetcode
 * Description:
 * Created by wangzheng on 2023/12/21 10:28
 * Copyright @ 2023 网易有道. All rights reserved.
 **/
abstract class A {

    abstract String getP();
    A() {
        System.out.println("A 构造函数：" + getP());
    }
}
