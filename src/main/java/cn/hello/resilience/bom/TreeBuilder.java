package cn.hello.resilience.bom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeBuilder {

    /**
     * 非递归形式建树
     * @param boms
     * @param rootProductCode
     * @return
     */
    public static List<BomBean> buildBomTree(List<BomBean> boms, String rootProductCode) {
        // 将节点按 id 建立映射，便于快速查找
        Map<String, BomBean> nodeMap = boms.stream()
                .collect(Collectors.toMap(BomBean::getProductCode, node -> node));

        // 存放根节点
        List<BomBean> roots = new ArrayList<>();

        for (BomBean node : boms) {
            if (Objects.equals(node.getSupProductCode(), rootProductCode)) {
                // 是根节点
                roots.add(node);
            } else {
                // 找到父节点，添加到其 children 中
                BomBean parent = nodeMap.get(node.getSupProductCode());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
                // 如果父节点不存在，可以选择忽略或抛异常
            }
        }

        return roots;
    }


    /**
     * 将扁平列表构建成树结构
     *
     * @param nodes 扁平的节点列表
     * @param rootPid 根节点的父 ID（通常为 null 或 0）
     * @return 树形结构的根节点列表（支持多个根）
     */
    public static List<TreeNode> buildTree(List<TreeNode> nodes, Long rootPid) {
        // 将节点按 id 建立映射，便于快速查找
        Map<Long, TreeNode> nodeMap = nodes.stream()
                .collect(Collectors.toMap(TreeNode::getId, node -> node));

        // 存放根节点
        List<TreeNode> roots = new ArrayList<>();

        for (TreeNode node : nodes) {
            if (Objects.equals(node.getPid(), rootPid)) {
                // 是根节点
                roots.add(node);
            } else {
                // 找到父节点，添加到其 children 中
                TreeNode parent = nodeMap.get(node.getPid());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
                // 如果父节点不存在，可以选择忽略或抛异常
            }
        }

        return roots;
    }
}