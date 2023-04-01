package com.neprozorro.rest;

import com.neprozorro.service.MockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequiredArgsConstructor
public class MockResource { //Todo for remove

    private final MockService saveService;

    @GetMapping("/mockdata")
    @ResponseStatus(HttpStatus.FOUND)
    public void save() {
        log.debug("save");
        try {
            saveService.save();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}
