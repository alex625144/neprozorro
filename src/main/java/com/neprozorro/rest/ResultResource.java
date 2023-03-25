package com.neprozorro.rest;

import com.neprozorro.model.ResultReport;
import com.neprozorro.service.ResultService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/laptops/dateRange?{date}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResultReport> getResultTable(@PathVariable LocalDate date) {
        List<ResultReport> resultReports = new ArrayList<>();
        try {
            resultReports =resultService.getResult(date);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return  resultReports;
    }
}
