package com.bst.controller;

import com.bst.model.TreeNode;
import com.bst.model.TreeRecord;
import com.bst.repository.TreeRecordRepository;
import com.bst.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BstController {

    @Autowired
    private TreeService treeService;

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    // Displays the page with the number entry form
    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enterNumbers";
    }

    // Processes the numbers: builds the BST, stores a JSON snapshot in the DB,
    // and returns the TreeNode object to be serialized as pretty JSON.
    @PostMapping("/process-numbers")
    @ResponseBody
    public TreeNode processNumbers(@RequestParam("numbers") String numbers,
                                   @RequestParam(value = "balanced", required = false) String balanced) {
        // Parse input numbers into an array
        String[] parts = numbers.split(",");
        int[] values = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            values[i] = Integer.parseInt(parts[i].trim());
        }

        // Build the BST (balanced or sequential)
        TreeNode root;
        if ("true".equals(balanced)) {
            root = treeService.createBalancedBST(values);
        } else {
            root = treeService.insertSequential(values);
        }
        
        // Convert the tree to JSON (for database storage) without affecting the response
        String treeJson = treeService.toJson(root);
        TreeRecord record = new TreeRecord(numbers, treeJson);
        treeRecordRepository.save(record);

        // Return the TreeNode object. Jackson will serialize it as pretty JSON.
        return root;
    }

    // Retrieves and displays all stored tree records
    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        model.addAttribute("allTrees", treeRecordRepository.findAll());
        return "previousTrees";
    }
}
