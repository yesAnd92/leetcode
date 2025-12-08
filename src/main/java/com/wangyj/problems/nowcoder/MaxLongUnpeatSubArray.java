package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


//最长子串
public class MaxLongUnpeatSubArray {


    public int maxLength(int[] arr) {
        // write code here

        if (arr==null||arr.length==0)
            return 0;

        int max = 0;
        //统计数字出现的次数
        Map<Integer, Integer> statMap = new HashMap<>();

        int left = 0;
        int right = 0;
        while (right < arr.length) {
            //left right 从头开始滑动,right先移动
            if (statMap.containsKey(arr[right])) {
                //存在则+1
                statMap.put(arr[right], statMap.get(arr[right]) + 1);
            } else {
                statMap.put(arr[right], 1);
            }



            //遇到重复的元素，left就需要一直移动到消除重复
            while (statMap.get(arr[right]) > 1) {

                statMap.put(arr[left], statMap.get(arr[left]) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);

            right++;
        }

        return max;
    }


    @Test
    public void test(){
//        int[] nums=new int[]{2,3,4,5};
//        int[] nums=new int[]{2,2,3,4,3};
        int[] nums=new int[]{1,2,3,1,2,3,2,2};
        System.out.println(maxLength(nums));
    }
}
