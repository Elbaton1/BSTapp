package com.bst;

import com.bst.model.TreeNode;
import com.bst.service.TreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BstApplicationTests {

    @Test
    void testInsertSequential() {
        TreeService service = new TreeService();
        int[] values = {5, 2, 8, 1, 3};
        TreeNode root = service.insertSequential(values);
        Assertions.assertNotNull(root);
        Assertions.assertEquals(5, root.value);
        Assertions.assertEquals(2, root.left.value);
        Assertions.assertEquals(8, root.right.value);
    }

    @Test
    void testCreateBalancedBST() {
        TreeService service = new TreeService();
        int[] values = {1, 2, 3, 4};
        TreeNode root = service.createBalancedBST(values);
        Assertions.assertNotNull(root);
    }

    @Test
    void testToJson() {
        TreeService service = new TreeService();
        int[] values = {2, 1, 4, 3};
        TreeNode root = service.insertSequential(values);
        String json = service.toJson(root);
        Assertions.assertTrue(json.contains("\"value\": 2"));
    }
}
