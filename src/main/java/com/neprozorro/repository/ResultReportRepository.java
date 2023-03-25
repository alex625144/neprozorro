package com.neprozorro.repository;

import com.neprozorro.model.ResultReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ResultReportRepository extends JpaRepository<ResultReport, UUID> {

    List<ResultReport> findResultReportsByDateAndDate(LocalDate startDate, LocalDate endDate);
}
