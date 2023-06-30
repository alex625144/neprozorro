package com.neprozorro.rest;

import com.neprozorro.model.LotInfo;
import com.neprozorro.rest.dto.LotInfoCriteriaRequestDto;
import com.neprozorro.rest.dto.LotInfoMapper;
import com.neprozorro.rest.dto.LotInfoResponseDTO;
import com.neprozorro.service.LotInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lot-info")
@RequiredArgsConstructor
public class LotInfoResource {

    private final LotInfoService lotInfoService;
    private final LotInfoMapper lotInfoMapper;

    @GetMapping
    public List<LotInfo> findAll() {
        return lotInfoService.findAll();
    }

    @GetMapping("/filter")
    public Page<LotInfoResponseDTO> findByCriteria(LotInfoCriteriaRequestDto lotInfoCriteriaRequestDto, Pageable pageable) {
        return lotInfoService.findByCriteria(lotInfoCriteriaRequestDto, pageable);
    }
}
