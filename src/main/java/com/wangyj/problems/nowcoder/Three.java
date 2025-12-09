package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Three {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> re = new ArrayList<>();

        //先排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //固定一个数

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    re.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }

                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum > 0) {
                    //由于升序,越往右越大
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
        int[] nums = new int[]{-2, 0, 1, 1, 2};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> l : lists) {
            for (Integer integer : l) {
                System.out.print(integer + " ");

            }
            System.out.println();
        }
    }
}
