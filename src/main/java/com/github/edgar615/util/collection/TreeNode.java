package com.github.edgar615.util.collection;

import java.util.List;

public interface TreeNode<T> {

  TreeNode addChild(TreeNode treeNode);

  T getParentId();

  T getId();

  List<TreeNode> getChildren();

  boolean isRoot();
}
