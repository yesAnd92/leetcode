package com.wangyj.problems.back_trace;

import com.azul.crs.client.service.QueueService;
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



    /*****************************************************回溯+剪枝(避免重复遍历)**************************************************/


    /**
     * 上边的方法是收集到所有的排列，利用Set做的去重
     * 这里实际上可以进一步优化，在添加结果时就保证不重复
     * 具体是将字符串中的字符排序，这样相同的字符就挨在一起，便于后续去重
     */
    LinkedList<String> ans2 = new LinkedList<>();

    public String[] permutation2(String s) {

        char[] chars = s.toCharArray();

        //对字符排序
        Arrays.sort(chars);
        //利用一个布尔数组表示当前访问的下标元素是否被访问过
        boolean[] used =new boolean[chars.length];
        //使用stringbuffer粗放被遍历到的路径
        StringBuffer sbTrace = new StringBuffer();

        dfs(chars,sbTrace, used);

        //转换成数组返回结果
        return ans2.toArray(new String[0]);
    }


    public void dfs(char[] chars,StringBuffer sbTrace, boolean[] used){

        //退出条件：遍历到最后一层，也就得到了结果
        if (chars.length==sbTrace.length()){
            ans2.add(sbTrace.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {

            //已经遍历过的元素直接跳过
            if (used[i])
                continue;

            /**
             * 这里是做剪枝的核心处理，used[i-1]==false在深度优先遍历过程中刚刚被撤销的选择，
             * 比如'aabc'，遍历完第一个a，撤销这个选择，选择第二个a，如果用这个a在遍历一遍，
             * 一定是和第一个a遍历重复的结果。
             */
            if (i>0&&chars[i]==chars[i-1]&&used[i-1]==false){
                continue;
            }

            //标记为已经遍历
            used[i]=true;
            //选择这个元素，加入路径中
            sbTrace.append(chars[i]);
            //进入下一层遍历
            dfs(chars, sbTrace, used);
            //撤销选择，标记为未遍历，并删除路径中的这个值
            used[i]=false;
            sbTrace.deleteCharAt(sbTrace.length()-1);

        }
    }

    @Test
    public void test() {
        String[] abcas = permutation2("abcaa");
        for (String a : abcas) {
            System.out.print(a + " ");
        }

    }
}
