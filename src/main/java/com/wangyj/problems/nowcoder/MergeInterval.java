package com.wangyj.problems.nowcoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MergeInterval {


    public class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // write code here
        ArrayList<Interval> result = new ArrayList<>();

        int len = intervals.size();
        if (len < 1) {
            return result;
        }

        Collections.sort(intervals, Comparator.comparingInt(o -> o.start));


        //结果集中下标位置，使用set而不是add是为了方便控制插入的位置
        int resultIdx = 0;
        result.add(intervals.get(0));

        for (int i = 1; i < len; i++) {
            //结果集中最后一个区间
            int preStart = result.get(resultIdx).start, preEnd = result.get(resultIdx).end;

            //当前遍历到的区间
            int currStart = intervals.get(i).start, currEnd = intervals.get(i).end;
            if (preEnd >= currStart) {
                //说明有交叉，则跟result中最后一个元素合并
                //左端点不变,比较两个右端点谁大，修改到结果集中
                //这里直接new一个新对象，规避改掉原对象的问题
                result.set(resultIdx, new Interval(preStart, Math.max(preEnd, currEnd)));

            } else {
                //不重叠，当前节点放到结果集中
                result.add(intervals.get(i));
                resultIdx++;
            }

        }


        return result;


    }


    @Test
    public void test() {
        //[10,30],[20,60],[80,100],[150,180]

        ArrayList<Interval> list = new ArrayList<>();
        list.add(new Interval(10, 30));
        list.add(new Interval(20, 60));
        list.add(new Interval(150, 180));
        list.add(new Interval(80, 100));

        ArrayList<Interval> merge = merge(list);
        for (Interval interval : merge) {
            System.out.println(interval.start + "--" + interval.end);
        }
    }
}
