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

    // Route to display the number entry form
    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enterNumbers";
    }

    // Route to process numbers, build the BST, store in DB and return JSON
    @PostMapping("/process-numbers")
    @ResponseBody
    public String processNumbers(@RequestParam("numbers") String numbers,
                                 @RequestParam(value = "balanced", required = false) String balanced) {
        String[] parts = numbers.split(",");
        int[] values = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            values[i] = Integer.parseInt(parts[i].trim());
        }

        TreeNode root;
        if (balanced != null && balanced.equals("true")) {
            root = treeService.createBalancedBST(values);
        } else {
            root = treeService.insertSequential(values);
        }
        String treeJson = treeService.toJson(root);

        TreeRecord record = new TreeRecord(numbers, treeJson);
        treeRecordRepository.save(record);

        return treeJson;
    }

    // Route to display previously saved trees
    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        model.addAttribute("allTrees", treeRecordRepository.findAll());
        return "previousTrees";
    }
}
