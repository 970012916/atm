package com.coderpig.drysisters;

public class ResUtils {

    /**
     * 获取文件资源
     */
    public static String getString(int strId) {
        return  DrySisterApp.getContext().getResources().getString(strId);
    }
}

