package cn.hello.resilience.bom;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RecursiveTreeBuilder {


    public static List<BomBean> buildBomTreeRecursively(List<BomBean> nodes, String rootProductCode) {
        List<BomBean> roots = new ArrayList<>();

        // 找出所有根节点（pid == rootPid）
        for (BomBean node : nodes) {
            if (!StringUtils.isEmpty(node.getSupProductCode()) && node.getSupProductCode().equals(rootProductCode)) {
                // 递归构建该根节点的子树
                BomBean root = new BomBean(node.getProductCode(), node.getSupProductCode());
//                此node的productCode 作为parentProductCode
                root.setChildren(buildBomChildren(nodes, node.getProductCode()));
                roots.add(root);
            }
        }
        return roots;
    }

    /**
     * 递归构建某个节点的所有子节点
     */
    private static List<BomBean> buildBomChildren(List<BomBean> allNodes, String parentProductCode) {
        List<BomBean> children = new ArrayList<>();

        for (BomBean node : allNodes) {
            if (node.getSupProductCode() != null && node.getSupProductCode().equals(parentProductCode)) {
                BomBean child = new BomBean(node.getProductCode(), node.getSupProductCode());
                // 递归构建孙子节点，递归， 此节点的productCode作为父code
                child.setChildren(buildBomChildren(allNodes, node.getProductCode()));
                children.add(child);
            }
        }
        return children;
    }

//    ------------------------------------------------------------------------------

    /**
     * 递归构建整棵树（支持多个根节点）
     */
    public static List<TreeNode> buildTreeRecursively(List<TreeNode> nodes, Long rootPid) {
        List<TreeNode> roots = new ArrayList<>();
        
        // 找出所有根节点（pid == rootPid）
        for (TreeNode node : nodes) {
            if (node.getPid() == null ? rootPid == null : node.getPid().equals(rootPid)) {
                // 递归构建该根节点的子树
                TreeNode root = new TreeNode(node.getId(), node.getPid());
                root.setChildren(buildChildren(nodes, node.getId()));
                roots.add(root);
            }
        }
        return roots;
    }

    /**
     * 递归构建某个节点的所有子节点
     */
    private static List<TreeNode> buildChildren(List<TreeNode> allNodes, Long parentId) {
        List<TreeNode> children = new ArrayList<>();
        
        for (TreeNode node : allNodes) {
            if (node.getPid() != null && node.getPid().equals(parentId)) {
                TreeNode child = new TreeNode(node.getId(), node.getPid());
                // 递归构建孙子节点
                child.setChildren(buildChildren(allNodes, node.getId()));
                children.add(child);
            }
        }
        return children;
    }
}