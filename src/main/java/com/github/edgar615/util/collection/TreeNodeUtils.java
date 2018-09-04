package com.github.edgar615.util.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/8/20.
 */
public class TreeNodeUtils {

  public static <T, U extends TreeNode> List<U> createTree(List<T> datas, Function<T, U> function) {
    List<U> nodes = datas
        .stream()
        .map(function)
        .collect(Collectors.toList());

    List<U> root = nodes.stream()
        .filter(n -> n.getParentId() == -1)
        .collect(Collectors.toList());

    Map<Integer, List<TreeNode>> grouping = nodes.stream()
        .filter(n -> n.getParentId() != -1)
        .collect(Collectors.groupingBy(n -> n.getParentId()));

    for (Integer parentId : grouping.keySet()) {
      nodes.stream()
          .filter(n -> n.getId().equals(parentId))
          .forEach(n -> grouping.get(parentId).stream().forEach(c -> n.addChild(c)));
    }
    return root;
  }

  public static void levelTraverse(TreeNode node, Consumer<TreeNode> consumer) {
    if (node == null) {
      return;
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.add(node);
    while (!queue.isEmpty()) {
      TreeNode tmp = queue.poll();
      consumer.accept(tmp);
      List<TreeNode> children = tmp.getChildren();
      if (children != null) {
        queue.addAll(children);
      }
    }
  }

  public static void deptTraverse(TreeNode node, Consumer<TreeNode> consumer) {
    if (node == null) {
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(node);
    while (!stack.isEmpty()) {
      TreeNode tmp = stack.pop();
      consumer.accept(tmp);
      List<TreeNode> children = tmp.getChildren();
      if (children != null) {
        children.stream().forEach(c -> stack.push(c));
      }
    }
  }
}
