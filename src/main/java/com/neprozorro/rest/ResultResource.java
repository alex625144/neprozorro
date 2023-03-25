package com.neprozorro.rest;

import com.neprozorro.model.ResultReport;
import com.neprozorro.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController("/api/v1")
@RequiredArgsConstructor
public class ResultResource {

    private final ResultService resultService;

    @GetMapping("/laptops/dateRange?from={startDate},to={endDate}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResultReport> getResultTable(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ResultReport> resultReports = new ArrayList<>();
        try {
            resultReports =resultService.getResult(startDate, endDate);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return  resultReports;
    }
}
