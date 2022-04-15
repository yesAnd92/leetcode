package com.wangyj.problems.math;

import org.junit.Test;


/**
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class Sqrtx_69 {


    /**
     * 使用二分法不断逼近平方根的值
     * https://leetcode-cn.com/problems/sqrtx/solution/er-fen-cha-zhao-niu-dun-fa-python-dai-ma-by-liweiw/
     */
    public int mySqrt(int x) {
        //特判
        if(x==0)
            return 0;
        if (x==1)
            return 1;

        int left =1;
        //值的方根通常是小于值的一半的，初始范围从[1,x/2]
        int right=x>>1;
        while (left<right){
            //特别要注意，这块有个技巧，计算mid值时可以使用上取整，这样可以避免最后区间内只剩两个值时导致的死循环问题
            int mid =left+(right-left+1)/2;
            //这里也要注意，不要写mid*mid，有溢出风险，使用除法规避
            if(x/mid<mid){
                //区间变更为[left,mid-1],因为mid*mid>x,所以mid肯定不是解，可将mid这个点排除
                right=mid-1;
            }else {
                left=mid;
            }

        }

        return left;
    }


    @Test
    public void test(){
        System.out.println(mySqrt(143));
    }

}
