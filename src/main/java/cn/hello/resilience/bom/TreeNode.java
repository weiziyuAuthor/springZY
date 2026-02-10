package cn.hello.resilience.bom;

import java.util.ArrayList;
import java.util.List;

//@Data
public class TreeNode {
    private Long id;
    private Long pid;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(Long id, Long pid) {
        this.id = id;
        this.pid = pid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{id=" + id + ", pid=" + pid + ", children=" + children + '}';
    }
}