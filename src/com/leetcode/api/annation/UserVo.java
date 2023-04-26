package com.leetcode.api.annation;

public class UserVo {
    @Init(set = "注解注入姓名")
    private String name;

    public String getName() {
        return name == null ? "" : name;
    }

}
