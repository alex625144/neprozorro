package com.neprozorro.rest.mockdata;

import com.neprozorro.service.mockdata.LotInfoMockDataGeneratorService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mockdata/lot-info-generator")
@Hidden
public class LotInfoMockDataGenerator {

    private final LotInfoMockDataGeneratorService lotInfoMockDataGeneratorService;

    @GetMapping
    public void generateLotInfoMockData() {
        lotInfoMockDataGeneratorService.generateLotInfoMockData();
    }
}
