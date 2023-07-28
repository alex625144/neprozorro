package com.neprozorro.rest.mockdata;

import com.neprozorro.service.mockdata.ReportsMockDataGeneratorService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mockdata/result-report-generator")
@Hidden
public class ResultReportsMockDataGenerator {

    private final ReportsMockDataGeneratorService mockService;

    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public void generateResultReportsMockData() {
        log.debug("save");
        try {
            mockService.generateResultReportsMockData();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }


}
