package com.wangyj.problems.nowcoder;

public class BigNumAdd {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public static String solve(String s, String t) {

        int sLength = s.length();
        int tLength = t.length();
        if (sLength == 0 && tLength == 0)
            return "0";
        if (sLength == 0)
            return t;
        if (tLength == 0)
            return s;
        //s倒序存入数字
        int[] sArr = new int[sLength];
        for (int i = sLength, j = 0; i > 0; i--) {
            sArr[j++] = s.charAt(i - 1) - '0';
        }

        //t倒序存入数字
        int[] tArr = new int[tLength];
        for (int i = tLength, j = 0; i > 0; i--) {
            tArr[j++] = t.charAt(i - 1) - '0';
        }

        int maxLen = Math.max(sLength, tLength);

        StringBuilder sb = new StringBuilder();
        int jinwei = 0;
        for (int i = 0; i < maxLen; i++) {

            int sEle = 0;
            if (i < sLength) {
                sEle = sArr[i];
            }
            int tEle = 0;
            if (i < tLength) {
                tEle = tArr[i];
            }

            sb.append((sEle + tEle) % 10 + jinwei);
            jinwei = (sEle + tEle) / 10;

        }
        if (jinwei > 0)
            sb.append(1);


        return sb.reverse().toString();
    }


    //直接一次遍历，倒着取数字并且相加，没必要分别便遍历一次取数字，既浪费空间，也浪费时间
    //两个字符串同时遍历，不知道长度的情况下，可以使用while代理for，更好写一点
    public static String solve_2(String s, String t) {

        int sLength = s.length();
        int tLength = t.length();
        if (sLength == 0 && tLength == 0)
            return "";
        if (sLength == 0)
            return t;
        if (tLength == 0)
            return s;


        StringBuilder sb = new StringBuilder();

        int sIdx = sLength - 1, tIdx = tLength - 1;

        int add = 0;
        while (sIdx >= 0 || tIdx >= 0) {
            int a = 0, b = 0;
            if (sIdx >= 0) {
                a = s.charAt(sIdx--) - '0';
            }

            if (tIdx >= 0) {
                b = t.charAt(tIdx--) - '0';
            }
            int total = a + b + add;
            sb.append(total % 10);
            add = total / 10;
        }

        if (add>0){
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    //这种写法更简洁，但是不好容易想到如何处理最高位进位，不如直接最后单独考虑
    public String solve_3 (String s, String t) {

        if (s.length() + t.length() == 0) {
            return "";
        }

        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;

        int add = 0;

        StringBuilder ans = new StringBuilder();
        while (sIndex >= 0 || tIndex >= 0||add!=0) {
            int a = 0, b = 0;
            if (sIndex >= 0) {
                a = s.charAt(sIndex--) - 48;
            }
            if (tIndex >= 0) {
                b = t.charAt(tIndex--) - 48;
            }
            int tmp = a + b + add;
            ans.append(tmp%10);
            add = tmp >= 10 ? 1 : 0;
        }

        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(solve("1234", "9543"));
        System.out.println(solve_2("1234", "9543"));
//        System.out.println('0' - 0);
    }

}
