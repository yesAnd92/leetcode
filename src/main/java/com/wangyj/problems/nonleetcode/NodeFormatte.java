package com.wangyj.problems.nonleetcode;



import java.util.*;

public class NodeFormatte {
    static class Node {
        private int id;
        private int parentId;
        private String name;

        public Node() {

        }

        public Node(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        NodeFormatte tree = new NodeFormatte();
        List<Node> nodeList = Arrays.asList(
                new Node(5, 3, "EE"),
                new Node(1, 0, "AA"),
                new Node(2, 1, "BB"),
                new Node(3, 1, "CC"),
                new Node(4, 3, "DD"),
                new Node(6, 2, "FF"),
                new Node(7, 2, "GG"),
                new Node(8, 4, "HH"),
                new Node(9, 5, "II"),
                new Node(10, 0, "JJ"));
        tree.convertList(nodeList);
    }

    /**
     * 结果要求打印： 不能更改Node结构
     *  AA
     *      BB
     *          FF
     *          GG
     *      CC
     *          DD
     *              HH
     *          EE
     *              II
     *  JJ
     * @param nodeList
     * @return
     */
    private void convertList(List<Node> nodeList) {

        Map<Integer,List<Node>>  parentChildrenListMap= new HashMap<>();
        for (Node node : nodeList) {
            int parentId = node.parentId;
            if (parentChildrenListMap.containsKey(parentId)){
                parentChildrenListMap.get(parentId).add(node);
            }else {
                List<Node> nodes=new ArrayList<>();
                nodes.add(node);
                parentChildrenListMap.putIfAbsent(parentId,nodes);
            }
        }

            List<Node> nodes = parentChildrenListMap.get(0);
            formmate(parentChildrenListMap,nodes,0);

        System.out.println();
    }


    private void formmate(Map<Integer,List<Node>>  parentChildrenListMap,List<Node> nodes ,int space){
            nodes.forEach(node -> {
                System.out.println(getSpaceTab(space)+node.getName());
                List<Node> subNodes = parentChildrenListMap.get(node.getId());
                if (subNodes!=null&&subNodes.size()>0){
                    formmate(parentChildrenListMap,subNodes,space+1);
                }
            });
    }

    private String getSpaceTab(int space){
        String s="";
        for (int i = 0; i < space; i++) {
            s+="\t";
        }
        return s;
    }
}
