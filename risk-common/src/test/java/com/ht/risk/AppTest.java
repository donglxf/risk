package com.ht.risk;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    public static void main(String[] args) {
        System.out.println(isSpecialChar());
    }

    public static boolean isSpecialChar() {
        String str = "你好啊.你";
        //String regEx = "^[_`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        String regEx = "^[^ `~!@#$%^&*()+=|{}''\\\\[\\\\].<>/~@#￥%……&*（）——+|{}]+$";
        regEx = "^[^&#$<>'\\\"\\\\|\\\\\\\\]*$";


        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
