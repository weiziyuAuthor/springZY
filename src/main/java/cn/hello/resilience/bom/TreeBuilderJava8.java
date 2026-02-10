package cn.hello.resilience.bom;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TreeBuilderJava8 {

    public static List<BomBean> buildBomTree(List<BomBean> nodes, String parentProductCode) {
        return nodes.stream()
//                .filter(node -> (rootPid == null ? node.getPid() == null : rootPid.equals(node.getPid())))
                .filter(node -> (!StringUtils.isEmpty(parentProductCode) && parentProductCode.equals(node.getSupProductCode())))
                .map(node -> {
                    BomBean root = new BomBean(node.getProductCode(), node.getSupProductCode());
                    root.setChildren(buildBomChildren(nodes, node.getProductCode()));
                    return root;
                })
                .collect(Collectors.toList());
    }

    private static List<BomBean> buildBomChildren(List<BomBean> allNodes, String parentProductCode) {
        return allNodes.stream()
                .filter(node -> parentProductCode.equals(node.getSupProductCode()))
                .map(node -> {
                    BomBean child = new BomBean(node.getProductCode(), node.getSupProductCode());
                    child.setChildren(buildBomChildren(allNodes, node.getProductCode())); // 递归
                    return child;
                })
                .collect(Collectors.toList());
    }

//    ---------------------------------------------------

    /**
     * 构建树：找出所有根节点，并为每个根递归构建子树
     */
    public static List<TreeNode> buildTree(List<TreeNode> nodes, Long rootPid) {
        return nodes.stream()
                .filter(node -> (rootPid == null ? node.getPid() == null : rootPid.equals(node.getPid())))
                .map(node -> {
                    TreeNode root = new TreeNode(node.getId(), node.getPid());
                    root.setChildren(buildChildren(nodes, node.getId()));
                    return root;
                })
                .collect(Collectors.toList());
    }

    /**
     * 递归构建子节点（使用 Stream + Lambda）
     */
    private static List<TreeNode> buildChildren(List<TreeNode> allNodes, Long parentId) {
        return allNodes.stream()
                .filter(node -> parentId.equals(node.getPid()))
                .map(node -> {
                    TreeNode child = new TreeNode(node.getId(), node.getPid());
                    child.setChildren(buildChildren(allNodes, node.getId())); // 递归
                    return child;
                })
                .collect(Collectors.toList());
    }
}