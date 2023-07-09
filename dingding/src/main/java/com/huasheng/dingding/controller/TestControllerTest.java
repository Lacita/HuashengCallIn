package com.huasheng.dingding.controller;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class TestControllerTest {

    public void test1(String word1, String word2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word1.length() || i < word2.length(); i++) {
            if (i < word1.length()) {
                stringBuilder.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                stringBuilder.append(word2.charAt(i));
            }
        }
    }


    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        return sub(str1, str2);
    }

    public static String sub(String str1, String str2) {
        if (str1.equals(str2)) {
            return str1;
        }
        if (str1.length() < str2.length()) {
            str1 = str1.substring(str1.length(), str2.length());
            return sub(str1, str2);
        }
        if ((str1.length()) > (str2.length())) {
            str2 = str2.substring(str2.length(), str1.length());
            return sub(str1, str2);
        }
        return "";
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        if (!(candies.length > 0) || !(extraCandies > 0)) {
            return null;
        }
        List<Boolean> checkMax = new ArrayList<>();
        int max = 0;
        for (int i = 0; i <= candies.length; i++) {
            max = Math.max(max, candies[i]);
        }
        for (int i = 0; i < candies.length; i++) {
            if (max <= (candies[i] + extraCandies)) {
                checkMax.add(true);
            } else {
                checkMax.add(false);
            }
        }
        return checkMax;
    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int num = 0, count = 1;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) {
                num++;
                count = 1;
            }
        }
        if (count == 2) {
            num++;
        }
        return n <= num;
    }

    public String reverseVowels(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (yuanYin(s.charAt(i))) {
                stack.add(s);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (yuanYin(s.charAt(i))) {
                builder.append(stack.pop());
            }
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }

    public boolean yuanYin(char s) {
        switch (s) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

}