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
        String str = "#121dsdsaaad#fafafdaf";
        //String regEx = "^[_`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        String regEx = "^[^ `~!@#$%^&*()+=|{}''\\\\[\\\\].<>/~@#￥%……&*（）——+|{}]+$";


        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
