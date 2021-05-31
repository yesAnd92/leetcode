package com.wangyj.problems.math;

import org.junit.Test;

/**
 *
 * @author W.Y.J
 * @Date 2021/5/30 18:20
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 */
public class FactorialTrailingZeroes_172 {


    /**
     * 这道题本质是一个数学规律总结题
     * 以10！为例 10*9*8*7*6*5*4*3*2*1 ，能出现0的就是10，8*5，6*5，4*5，2*5，
     * 进行因式分解后发现，其实只要有5*2就会产生0，而且2的数量远大于5个数，问题就转换为有多少个5了
     */
    public int trailingZeroes(int n) {
        int count=0;
        //4以下不包含5因子
        for (int i = n; i >4 ; i--) {
            int tmp=i;
            while (tmp%5==0){
                count++;
                tmp/=5;
            }
        }
        return count;
    }

    @Test
    public void test(){
        System.out.println(trailingZeroes(3));
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(10));
        System.out.println(trailingZeroes(15));
        System.out.println(trailingZeroes(20));
        System.out.println(trailingZeroes(25));
        System.out.println(trailingZeroes(30));
    }
}
