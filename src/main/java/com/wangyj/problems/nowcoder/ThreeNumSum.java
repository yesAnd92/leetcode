package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumSum {


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param num int整型一维数组
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {

        ArrayList<ArrayList<Integer>> re = new ArrayList<>();

        int length = num.length;
        if (num == null || length < 3) {
            return re;
        }


        //排序的目的是避免重复解，题目中也进行了提示。三个元素要升序
        Arrays.sort(num);


        for (int i = 0; i < length; i++) {

            //优化：如果三个最小的数中，都已经>0,后边的情况无需在看
            if (num[i] > 0) {
                break;
            }

            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if (i > 0 && num[i] == num[i - 1])
                continue;

            //因为三数和是0，考虑固定一个位置，用双指针，首尾进行遍历，这样效率高一些
            int left = i + 1, right = length - 1;
            while (left < length && left < right) {
                int sum = num[i] + num[left] + num[right];
                if (sum == 0) {
                    ArrayList<Integer> ele = new ArrayList() {
                    };
                    //考虑连续重复导致的问题
                    while (left<right&&num[left+1]==num[left]){
                        left++;
                    }

                    while (left<right&&num[right]==num[right-1]){
                        right--;
                    }
                    ele.add(num[i]);
                    ele.add(num[left]);
                    ele.add(num[right]);
                    re.add(ele);

                    right--;
                    left++;

                } else if (sum > 0) {
                    //和大于零了，说明需要更小的数字，由于是num是升序的，因此right要左移，才有机会和为0
                    right--;
                } else {
                    left++;
                }
            }
        }

        return re;
    }


    @Test
    public void test() {
        int[] nums = new int[]{-10, 0, 10, 20, -10, -40};
//        int[] nums=new int[]{-2,0,1,1,2};
        ArrayList<ArrayList<Integer>> arrayLists = threeSum(nums);
        for (ArrayList<Integer> arrayList : arrayLists) {
            System.out.println(arrayList);
        }
    }

}
