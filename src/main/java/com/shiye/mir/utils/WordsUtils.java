package com.shiye.mir.utils;

/**
 * 字符串功能类
 * @author fangshaozu
 */
public class WordsUtils {

    public static String suffixAbortion(String input){
        String[] a = input.split("\\.");
        return a[0];
    }

    /**
     * 将空格转化为下划线
     */
    public static String blankToUnder(String input){
        String result = "__";
        if(input!=null){
            result = input.replace(" ", "_");
        }
        return result;
    }
}
