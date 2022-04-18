package com.wangyj.problems.back_trace;

import org.junit.Test;

import java.util.*;


/**
 * 字符串排列
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
public class StringPermute_Offer38 {

    //用Set存放临时的结果，方便去重
    Set<String> ans = new HashSet<>();

    public String[] permutation(String s) {

        char[] chars = s.toCharArray();

        /**
         * 将遍历过的下标路径记录下来，为啥不直接记录对应的字符呢？
         * 因为字符可能重复，比如‘abca’,第二个a就无法被遍历到了
         */
        List<Integer> traces = new LinkedList<>();

        //开始遍历
        backTrace(traces, chars);

        //处理最终结果
        String[] res = new String[ans.size()];
        Iterator<String> iterator = ans.iterator();
        int i=0;
        while (iterator.hasNext()){
            res[i++]=iterator.next();
        }

        return res;
    }


    public void backTrace(List<Integer> traces, char[] chars) {

        //这是回溯递归过程的退出条件，遍历到最后一个元素，也就是traces个数和数组相等
        if (chars.length == traces.size()) {
            StringBuffer sb = new StringBuffer();
            traces.stream().forEach(i -> sb.append(chars[i]));
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            //已经遍历过，就不在重复遍历
            if (traces.contains(i))
                continue;
            //做选择
            traces.add(i);
            //进入下一层决策树
            backTrace(traces, chars);
            //取消选择
            traces.remove(Integer.valueOf(i));
        }
    }


    @Test
    public void test() {
        String[] abcas = permutation("ab");
        for (String a : abcas) {
            System.out.print(a + " ");
        }

    }
}
