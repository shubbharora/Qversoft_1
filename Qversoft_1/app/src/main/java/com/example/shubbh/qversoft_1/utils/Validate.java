package com.example.shubbh.qversoft_1.utils;

/**
 * Created by Shubbh on 6/19/2017.
 */

public class Validate {

    public static boolean isEmpty(String data)
    {
        if(data!=null&&data.length()>0)
            return true;
        else
            return false;
    }
}
