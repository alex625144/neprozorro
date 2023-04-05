package com.neprozorro.service;

import com.neprozorro.model.ResultReport;
import com.neprozorro.repository.ResultReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ResultService {

    private final ResultReportRepository resultReportRepository;

    @Transactional
    public List<ResultReport> getResult(LocalDate startDate, LocalDate endDate) {
        return resultReportRepository.findResultReportsByDateBetween(startDate, endDate);
    }
}
