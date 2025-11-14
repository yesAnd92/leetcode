package com.wangyj.problems.nowcoder;

import java.util.HashMap;

public class TwoSum {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param numbers int整型一维数组
     * @param target  int整型
     * @return int整型一维数组
     */
    public int[] twoSum(int[] numbers, int target) {
        // write code here
        int left=0,right=0;

        HashMap<Integer ,Integer> valueIndexMap = new HashMap();
        for (int i = 0; i < numbers.length; i++) {
            int another = target - numbers[i];
            if (valueIndexMap.containsKey(another)) {
                left=valueIndexMap.get(another)+1;
                right=i+1;
                break;
            }else {
                valueIndexMap.put(numbers[i],i);
            }
        }

        return new int[]{left, right};
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{20,70,110,150};
//        int target=90;
//        int nums[]=new int[]{3,2,4};
//        int target=6;
        int nums[] = new int[]{0,98, 50,70,50, 0, 150,50,};
        int target = 0;
        TwoSum twoSum = new TwoSum();
        int[] re = twoSum.twoSum(nums, target);
        System.out.println(re[0]+","+re[1]);
    }
}
