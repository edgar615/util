package com.github.edgar615.util.collection;

import java.util.List;

public interface TreeNode {
  Integer getParentId();

  Integer getId();

  TreeNode addChild(TreeNode treeNode);

  List<TreeNode> getChildren();
}
