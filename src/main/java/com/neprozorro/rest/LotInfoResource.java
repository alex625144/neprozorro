package com.neprozorro.rest;

import com.neprozorro.model.LotInfo;
import com.neprozorro.rest.dto.LotInfoMapper;
import com.neprozorro.rest.dto.LotInfoResponseDTO;
import com.neprozorro.service.LotInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<LotInfoResponseDTO> findByCriteria(
            @RequestParam(required = false) String buyer,
            @RequestParam(required = false) String seller,
            @RequestParam(required = false) String lotStatus,
            @RequestParam(required = false) String dk,
            @RequestParam(required = false) String lotTotalPrice,
            @RequestParam(required = false) String participants,
            @RequestParam(required = false) String lotURL,
            @RequestParam(required = false) String pdfURL,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String sortByColumn
            ) {

        return lotInfoService.findByCriteria(lotInfoMapper.toCriteriaDTO(buyer, seller, lotStatus,
                                                        dk, lotTotalPrice, participants, lotURL, pdfURL,
                                                            pageNo, pageSize, sort, sortByColumn));
    }
}
