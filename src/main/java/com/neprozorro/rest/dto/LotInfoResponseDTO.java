package com.neprozorro.rest.dto;

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
    private List<Participant> participantNames;
    private String lotURL;
    private String pdfURL;
    private List<LotItemInfo> lotItems;

    public void setParticipantNames(List<Participant> participantNames) {
        if (participantNames.isEmpty()){
            participantNames = null;
        }
        this.participantNames = participantNames;
    }

    public void setLotItems(List<LotItemInfo> lotItems) {
        if(lotItems.isEmpty()){
            lotItems = null;
        }
        this.lotItems = lotItems;
    }
}
