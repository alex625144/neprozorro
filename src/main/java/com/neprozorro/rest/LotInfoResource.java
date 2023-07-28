package com.neprozorro.rest;

import com.neprozorro.model.LotInfo;
import com.neprozorro.rest.dto.LotInfoCriteriaRequestDto;
import com.neprozorro.rest.dto.LotInfoMapper;
import com.neprozorro.rest.dto.LotInfoResponseDTO;
import com.neprozorro.service.LotInfoService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lot-info")
@RequiredArgsConstructor
public class LotInfoResource {

    private final LotInfoService lotInfoService;
    private final LotInfoMapper lotInfoMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LotInfo>> findAll() {
        return ResponseEntity.ok(lotInfoService.findAll());
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @Hidden
    public ResponseEntity<Page<LotInfoResponseDTO>> findByCriteria(LotInfoCriteriaRequestDto lotInfoCriteriaRequestDto, Pageable pageable) {
        return ResponseEntity.ok(lotInfoService.findByCriteria(lotInfoCriteriaRequestDto, pageable));
    }
}
