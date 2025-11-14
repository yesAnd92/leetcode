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

        String[] sArr = s.split(" ", -1);


        StringBuilder re = new StringBuilder();
        for (int i = sArr.length - 1; i >= 0; i--) {
            String word = upCaseTrans(sArr[i]);
            re.append(word);
            if (i != 0) {
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
//        System.out.println(trans("h i ", 4));
//        System.out.println(trans("XVWqpiqZUYhRVuC q", 17));

        System.out.println(trans_2("h i ", 4));
        System.out.println(trans_2("This is a sample",16));
    }


    //这种问题使用二次反转是最好解决的
    //找到挨个单词，反转后并原位置替换，可以规避空格问题
    public static String trans_2(String s, int n) {
        // write code here
        if (s == "") {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        //1、先替换大小写
        for (char c : s.toCharArray()) {
            //使用字符工具，代码更简洁
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
            }
            //空格不处理
            sb.append(c);
        }

        System.out.println(sb);

        //2、单词逆序
        for (int i = 0; i < n; i++) {
            int j = i;
            //j是移动的指针，右移知道遇到空格
            while (j < n && sb.charAt(j) != ' ') {
                j++;
            }
            String word = sb.substring(i, j);
            StringBuilder wordSb = new StringBuilder(word);
            //使用相同位置替换，规避掉空格问题
            sb.replace(i,j,wordSb.reverse().toString());
            //起点右移，看下一个单词
            i=j;
        }

        System.out.println(sb);

        //3. 再次整个字符串反转，即可获得
        return sb.reverse().toString();


    }

}
