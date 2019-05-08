package problems.dynamic_programming;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 */
public class WordBreak139 {
    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("apple", "pen");
        System.out.println(wordBreak2("applepenapple", wordDict));
    }


    public static boolean wordBreak2(String s, List<String> wordDict) {

        int length =s.length();
        //设置opt[i]为从0到i中s.subString(0,i)是否有子串可以组成
        boolean[] opt=new boolean[length];
        opt[0]=true;
        for(int i=1;i<=length;i++){
            for (int j=0;j<i;j++){
                //从下标j-->i的子串串是否可以用wordDict的元素表示
                boolean contain =wordDict.contains(s.substring(j,i));
                if (opt[j]&&contain){  //j前后的子串都可以被wordDict组成
                    opt[i]=true;
                    break;
                }
            }
        }
        return opt[length];
    }

    /**
     * 使用wordDict的子串进行组合后与目标字符串进行对比
     * 这种方法解决不了一个子串频繁被使用的问题
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> optList = new HashMap<>();
        optList.put(0, Arrays.asList(wordDict.get(0)));
        int length = wordDict.size();
        for (int i = 1; i < length; i++) {
            List<String> opt = new ArrayList<>();
            List<String> beforeOpt = optList.get(i - 1);
            String word = wordDict.get(i);
            for (String ele : beforeOpt) {
                opt.add(ele);
                opt.add(ele + word);
                opt.add(word + ele);
            }
            opt.add(word);
            optList.put(i, opt);
        }
        boolean result = false;
        for (String str : optList.get(length - 1)) {
            if (str.equals(s)) {
                result = true;
            }
        }
        return result;
    }
}
