package com.cn.hnust.domain;

public class UserWithBLOBs extends User {
    private String desc;

    private byte[] blob;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }
}