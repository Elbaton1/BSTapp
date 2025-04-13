package com.bst.repository;

import com.bst.model.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
}
