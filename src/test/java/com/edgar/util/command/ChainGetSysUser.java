package com.edgar.util.command;

public class ChainGetSysUser implements Command {

    private int userId;

    public ChainGetSysUser(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
