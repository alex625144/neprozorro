package com.neprozorro.repository;

import com.neprozorro.model.ResultReportItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResultReportItemRepository extends JpaRepository<ResultReportItem, UUID> {
}
