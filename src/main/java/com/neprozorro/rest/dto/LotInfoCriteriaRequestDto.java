package com.neprozorro.rest.dto;

import com.neprozorro.model.LotStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Schema(
        title = "criteria of lot information",
        description = "criteria witch uses in filter"
)
public class LotInfoCriteriaRequestDto {

    @Schema(example = "Олекс")
    private String buyer;

    @Schema(example = "Викт")
    private String seller;

    private List<LotStatus> lotStatus;

    @Schema(example = "009023")
    private String dk;

    @Schema(example = "10000,20000")
    private String lotTotalPrice;

    @Schema(example = "")
    private String participants;

    @Schema(example = "")
    private String lotURL;

    @Schema(example = "")
    private String pdfURL;
}
