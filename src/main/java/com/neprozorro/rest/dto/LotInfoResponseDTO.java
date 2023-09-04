package com.neprozorro.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neprozorro.model.LotItemInfo;
import com.neprozorro.model.LotStatus;
import com.neprozorro.model.Participant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class LotInfoResponseDTO {

    private UUID id;
    private String buyer;
    private String seller;
    private LotStatus lotStatus;
    private String dk;
    private BigDecimal lotTotalPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Participant> participantNames;
    private String lotURL;
    private String pdfURL;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LotItemInfo> lotItems;
}
