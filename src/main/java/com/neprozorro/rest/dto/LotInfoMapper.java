package com.neprozorro.rest.dto;

import com.neprozorro.model.LotInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LotInfoMapper {

    @Mapping(target = "buyer", source = "lotInfo.buyer.name")
    @Mapping(target = "seller", source = "lotInfo.seller.name")
    @Mapping(target = "participantNames", source = "lotInfo.participants")
    @Mapping(target = "lotStatus", source = "lotStatus.description")
    LotInfoResponseDTO toDto(LotInfo lotInfo);
}
