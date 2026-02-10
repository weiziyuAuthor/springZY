package cn.hello.resilience.bom;


import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        oldTreeNodeTest();
        bomTreeTest();
    }

    private static void bomTreeTest() {
//        init tree data
        List<BomBean> bomBeanList = initBomTree();

//        build tree by common method
//        List<BomBean> treeCommon =TreeBuilder.buildBomTree(bomBeanList, "0");
//        System.out.println(JSONArray.toJSON(treeCommon));

//        build tree by recursion method
//        // 假设根节点的 pid 是 0
//        List<BomBean> tree = RecursiveTreeBuilder.buildBomTreeRecursively(bomBeanList, "0");
//        System.out.println(JSONArray.toJSON(tree));

//       build tree by jdk8 feature
        List<BomBean> treeJDK8 =TreeBuilderJava8.buildBomTree(bomBeanList, "0");
        System.out.println(JSONArray.toJSON(treeJDK8));
    }

    /***
     * |--1
     * |----2_1
     * |------3_1
     * |------3_2
     * |----2_2
     * |----2_3
     */
    private static List<BomBean> initBomTree() {
//        BomBean(String modelId, Integer systemLevel, String productCode, String supProductCode, Integer num)
        List<BomBean> flatList = Arrays.asList(
                new BomBean("modelA", 1, "PC1", "0", 1),
                new BomBean("modelA", 3, "PC3_1", "PC2_1", 1),
                new BomBean("modelA", 3, "PC3_2", "PC2_1", 1),
                new BomBean("modelA", 2, "PC2_1", "PC1", 1),
                new BomBean("modelA", 2, "PC2_2", "PC1", 1),
                new BomBean("modelA", 2, "PC2_3", "PC1", 1)
        );
        return flatList;
    }

    private static void oldTreeNodeTest() {
        List<TreeNode> flatList = Arrays.asList(
                new TreeNode(1L, 0L),
                new TreeNode(2L, 1L),
                new TreeNode(3L, 1L),
                new TreeNode(4L, 2L),
                new TreeNode(5L, 2L),
                new TreeNode(6L, 0L),
                new TreeNode(7L, 6L)
        );

//        // 假设根节点的 pid 是 0
        List<TreeNode> tree = RecursiveTreeBuilder.buildTreeRecursively(flatList, 0L);
//        tree.forEach(System.out::println);
        System.out.println(JSONArray.toJSON(tree));

//        List<TreeNode> tree1 =TreeBuilder.buildTree(flatList, 0L);
//        System.out.println(JSONArray.toJSON(tree1));

//        List<TreeNode> tree1 =TreeBuilderJava8.buildTree(flatList, 0L);
//        System.out.println(JSONArray.toJSON(tree1));
    }
}