package com.neprozorro.rest.dto;

import com.neprozorro.model.LotInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Sort;

@Mapper(componentModel = "spring")
public interface LotInfoMapper {

    @Mapping(target = "buyer", source = "lotInfo.buyer.name")
    @Mapping(target = "seller", source = "lotInfo.seller.name")
    @Mapping(target = "participantNames", source = "lotInfo.participants")
    LotInfoResponseDTO toDto(LotInfo lotInfo);

    default LotInfoCriteriaRequestDto toCriteriaDTO(String buyer, String seller, String lotStatus, String dk, String lotTotalPrice,
                                                    String participants, String lotURL, String pdfURL,
                                                    Integer pageNo, Integer pageSize, String sort, String sortByColumn) {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        if (pageNo != null && pageNo > 0) {
            pageRequestDTO.setPageNo(pageNo);
        }
        if (pageSize != null && pageSize > 0) {
            pageRequestDTO.setPageSize(pageSize);
        }
        if (sort != null && sort.equals("ASC")) {
            pageRequestDTO.setSort(Sort.Direction.ASC);
        }
        if (sortByColumn != null) {
            pageRequestDTO.setSortByColumn(sortByColumn);
        }

        return LotInfoCriteriaRequestDto.builder()
                .buyer((buyer))
                .seller(seller)
                .lotStatus(lotStatus)
                .dk(dk)
                .lotTotalPrice(lotTotalPrice)
                .participants(participants)
                .lotURL(lotURL)
                .pdfURL(pdfURL)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
