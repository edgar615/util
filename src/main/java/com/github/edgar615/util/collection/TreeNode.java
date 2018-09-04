package com.github.edgar615.util.collection;

import java.util.List;

public interface TreeNode {
    TreeNode addChild(TreeNode treeNode);

    Integer getParentId();

    Integer getId();

    List<TreeNode> getChildren();
}
