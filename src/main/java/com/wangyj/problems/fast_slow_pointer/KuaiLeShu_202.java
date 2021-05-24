package com.wangyj.problems.fast_slow_pointer;

import org.junit.Test;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/happy-number/
 */
public class KuaiLeShu_202 {
     private int nextNum(int n){
         int sum  = 0;
         while (n>0){
             int mod =n%10;
             sum+=mod*mod;
             n=n/10;
         }
         return sum;
     }


    public boolean isHappy(int n) {
         //用set判读是否重复过
        HashSet<Integer> set= new HashSet();
         while (true){
             if (n==1)return true;
             if (set.contains(n)){
                 return false;
             }
             set.add(n);
             n=nextNum(n);
         }
    }

    /**
     * 其实最终结果就只有三种情况
     * 1、最终和为1，即快乐数
     * 2、数字在几个数字中间循环，形成了环
     * 3、数字越来越大（这种情况实际上并不存在 https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/）
     *
     * 到这可以想到使用快慢指针进行处理
     */
    public boolean isHappy2(int n) {
        int slow =n,fast=nextNum(n);
        while (fast!=1&&slow!=fast){
            slow=nextNum(slow);
            fast=nextNum(nextNum(fast));
        }
        return fast==1;
    }

     @Test
     public void test(){
         System.out.println(isHappy(2));
     }
}
