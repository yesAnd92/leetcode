package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.*;

/**
 * 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class PhoneNumber_17 {

    private static Map<Character, String> dicMap = new HashMap<>(8);

    private List<String> ans = new ArrayList<>(8);

    static {
        //初始化数据
        dicMap.put('2', "abc");
        dicMap.put('3', "def");
        dicMap.put('4', "ghi");
        dicMap.put('5', "jkl");
        dicMap.put('6', "mno");
        dicMap.put('7', "pqrs");
        dicMap.put('8', "tuv");
        dicMap.put('9', "wxyz");
    }


    /**
     *  注意这个题和全排列还是有些区别的，全排列的题目中，每个字符都是有可能作为第一元素
     *  但是这个题中数据的字符是有顺序，所以要控制递归中开始递归的位置
     */
    public List<String> letterCombinations(String digits) {

        int length = digits.length();
        if (length==0)
            return ans;
        String traces = "";
        char[] chars = digits.toCharArray();

        backTrace(traces, chars, 0, length);
        return ans;
    }


    /**
     *
     * @param traces  保存遍历的路径
     * @param chars  给的参数
     * @param curr   当前递归开始的位置，用于保证按照字符有序递归（区别于全排列的场景）
     * @param length 给定的参数的长度，一个数字对应一个字符，所以也就是结果的长度
     */
    private void backTrace(String traces, char[] chars, int curr, int length) {

        //每个数字对应一个字符，退出条件
        if (length == traces.length()) {
            ans.add(traces);
        }

        //从 curr开始迭代，避免将前边的数据重复遍历
        for (int i = curr; i < length; i++) {
            String dic = dicMap.get(chars[i]);
            curr++;
            //当前数字对应的字符各个情况都要遍历，再交给下一次递归
            for (int j = 0; j < dic.length(); j++) {
                backTrace(traces + dic.charAt(j), chars, curr, length);
            }
        }


    }



    /*****************************************************队列法**************************************************/


    /**
     * 初始化的这个字典表也可以用数组代替map
     * 数组下标位置隐含
     */
    private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };


    /**
     * 我们也可以使用队列，先将输入的 digits 中第一个数字对应的每一个字母入队，然后将出队的元素与第二个数字对应的每一个字母组合后入队...
     * 直到遍历到 digits 的结尾。最后队列中的元素就是所求结果。
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {

        int length = digits.length();
        if (length==0)
            return ans;

        char[] chars = digits.toCharArray();

        Queue<String> queue=new ArrayDeque<>();

        //往队列里塞入一个""空值，方便统一写代码
        queue.add("");
        for (int i = 0; i < length; i++) {
            String s = letterMap[chars[i]-'0'];
            //在遍历前记录队列当前层有几个元素，防止遍历到新加入到队列中的元素
            int tmpSize = queue.size();
            while (tmpSize>0){
                tmpSize--;
                String ele = queue.poll();
                for (int j = 0; j < s.length(); j++) {
                    queue.add(ele+s.charAt(j));
                }
            }
        }
        return new ArrayList<>(queue);
    }


    @Test
    public void test() {
        List<String> strings = letterCombinations2("23");
        List<String> strings2 = letterCombinations2("");
        System.out.println(strings);
        System.out.println(strings2);
    }
}
