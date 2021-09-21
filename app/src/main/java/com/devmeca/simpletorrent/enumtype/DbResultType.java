package com.devmeca.simpletorrent.enumtype;

public enum DbResultType {
    SUCCESS("SUCCESS", "success"),
    FAIL("FAIL", "fail");

    private String code;
    private String name;

    DbResultType(String code, String name) {
        this.code = code; this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
