package com.bst.service;

import com.bst.model.TreeNode;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class TreeService {

    
    public TreeNode insertSequential(int[] values) {
        TreeNode root = null;
        for (int value : values) {
            root = insertIntoBST(root, value);
        }
        return root;
    }

    private TreeNode insertIntoBST(TreeNode root, int value) {
        if (root == null) return new TreeNode(value);
        if (value < root.value) {
            root.left = insertIntoBST(root.left, value);
        } else {
            root.right = insertIntoBST(root.right, value);
        }
        return root;
    }

    
    public TreeNode createBalancedBST(int[] values) {
        Arrays.sort(values);
        return buildBalanced(values, 0, values.length - 1);
    }

    private TreeNode buildBalanced(int[] values, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(values[mid]);
        node.left = buildBalanced(values, start, mid - 1);
        node.right = buildBalanced(values, mid + 1, end);
        return node;
    }

    
    public String toJson(TreeNode root) {
        if (root == null) return "null";
        return "{ \"value\": " + root.value +
               ", \"left\": " + toJson(root.left) +
               ", \"right\": " + toJson(root.right) +
               " }";
    }
}
