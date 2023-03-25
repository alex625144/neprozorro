package com.neprozorro.repository;

import com.neprozorro.model.ResultReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResultReportRepository extends JpaRepository<ResultReport, UUID> {
}
