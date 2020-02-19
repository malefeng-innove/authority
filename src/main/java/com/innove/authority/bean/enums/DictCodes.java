package com.innove.authority.bean.enums;

public enum DictCodes {

    LOGIN_TYPE_PC("pc"),
    LOGIN_TYPE_WECHAT("wechat")
    ;

    private String type;

    DictCodes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
