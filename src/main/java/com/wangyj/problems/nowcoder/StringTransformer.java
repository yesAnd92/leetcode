package com.wangyj.problems.nowcoder;

public class StringTransformer {


    /**
     * 注意避免使用splite()，因为连续的空格场景处理起来很头疼
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串
     * @param n int整型
     * @return string字符串
     */
    public static String trans(String s, int n) {
        // write code here
        if (s == "") {
            return s;
        }

        String[] sArr = s.split(" ",-1);


        StringBuilder re = new StringBuilder();
        for (int i = sArr.length - 1; i >= 0; i--) {
            String word = upCaseTrans(sArr[i]);
            re.append(word);
            if (i!=0){
                re.append(" ");
            }
        }

        return re.toString();
    }

    public static String upCaseTrans(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'a') {
                c = (char) (c - 32);
            } else if (c <= 'Z') {
                c = (char) (c + 32);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println('a' - 'A');
//        System.out.println('a' - 'b');

//        System.out.println(trans("This   is a sample",16));
        System.out.println(trans("h i ", 4));
        System.out.println(trans("XVWqpiqZUYhRVuC q",17));
    }
}
