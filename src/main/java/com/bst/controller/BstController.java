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

    
    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enterNumbers";
    }

    
    @PostMapping("/process-numbers")
    @ResponseBody
    public TreeNode processNumbers(@RequestParam("numbers") String numbers,
                                   @RequestParam(value = "balanced", required = false) String balanced) {
        
        String[] parts = numbers.split(",");
        int[] values = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            values[i] = Integer.parseInt(parts[i].trim());
        }

        
        TreeNode root;
        if ("true".equals(balanced)) {
            root = treeService.createBalancedBST(values);
        } else {
            root = treeService.insertSequential(values);
        }
        
        
        String treeJson = treeService.toJson(root);
        TreeRecord record = new TreeRecord(numbers, treeJson);
        treeRecordRepository.save(record);

        
        return root;
    }

    
    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        model.addAttribute("allTrees", treeRecordRepository.findAll());
        return "previousTrees";
    }
}
