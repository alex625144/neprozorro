package com.neprozorro.rest.dto;

import com.neprozorro.model.LotStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.List;

@Getter
@Setter
@Builder
public class LotInfoCriteriaRequestDto {

    private String buyer;
    private String seller;
    private List<LotStatus> lotStatus;
    private String dk;
    private String lotTotalPrice;
    private String participants;
    private String lotURL;
    private String pdfURL;
}
