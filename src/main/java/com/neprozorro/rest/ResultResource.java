package com.neprozorro.rest;

import com.neprozorro.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ResultResource {

    private final ResultService resultService;

    @GetMapping("/laptops/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getResultTable(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
            return ResponseEntity.ok(resultService.getResult(startDate, endDate));
    }
}
