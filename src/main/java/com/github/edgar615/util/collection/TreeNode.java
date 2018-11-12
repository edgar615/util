package com.github.edgar615.util.collection;

import java.util.List;

public interface TreeNode<T> {

  TreeNode<T> addChild(TreeNode<T> treeNode);

  T getParentId();

  T getId();

  List<TreeNode<T>> getChildren();

  boolean isRoot();
}
