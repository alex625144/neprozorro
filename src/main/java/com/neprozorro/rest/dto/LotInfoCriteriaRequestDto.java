package com.neprozorro.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class LotInfoCriteriaRequestDto {

    private String buyer;
    private String seller;
    private String lotStatus;
    private String dk;
    private String lotTotalPrice;
    private String participants;
    private String lotURL;
    private String pdfURL;
}
