package com.wangyj.problems.double_pointer;

import org.junit.Test;

public class _680 {

    public boolean validPalindrome(String s) {

        char[] chars = s.toCharArray();
        int left=0,right=s.length()-1;
        boolean hasRemoved=false;

        while (left<=right){
            if (chars[left]==chars[right]){
                left++;
                right--;
                continue;
            }

            if (hasRemoved)
                return false;

            hasRemoved=true;
            if (chars[left+1]==chars[right]){
                //移除左边一个元素后相等
                left++;
            }else if (chars[left]==chars[right-1]){
                //移除右边一个元素后相等
                right--;
            }else {
                return false;
            }

        }
        return true;
    }


    @Test
    public void test(){
        String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
//        String s = "abbcac";
        System.out.println(validPalindrome(s));
    }
}
