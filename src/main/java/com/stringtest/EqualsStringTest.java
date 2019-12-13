package com.stringtest;

public class EqualsStringTest {
    public static void main(String[] args) {
        String s1 = "programming";
        String s2 = new String("programming");
        String s3 = "program";
        String s4 = "ming";
        String s5 = "program" + "ming";
        String s6 = s3+s4;

        System.out.println(s1==s2);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);

    }
}
