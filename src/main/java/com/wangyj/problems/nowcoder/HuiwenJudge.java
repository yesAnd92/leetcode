package com.wangyj.problems.nowcoder;

import org.junit.Test;

public class HuiwenJudge {

    public boolean judge(String str) {
        // write code here
        if (str == null || str == "") {
            return false;
        }
        //双指针，向中间遍历

        int left=0,right=str.length()-1;
        while (left<=right){
            if (str.charAt(left++)!=str.charAt(right--)){
                return false;
            }
        }

        return true;
    }


    @Test
    public void test(){
        System.out.println(judge("absba"));
        System.out.println(judge("abssba"));
        System.out.println(judge("a"));
        System.out.println(judge("abc"));
    }

}
