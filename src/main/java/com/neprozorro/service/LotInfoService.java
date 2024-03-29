package com.neprozorro.service;

import com.neprozorro.model.LotInfo;
import com.neprozorro.model.LotStatus;
import com.neprozorro.repository.LotInfoRepository;
import com.neprozorro.rest.dto.LotInfoResponseDTO;
import com.neprozorro.rest.dto.LotInfoMapper;
import com.neprozorro.rest.dto.LotInfoCriteriaRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LotInfoService {

    private final LotInfoRepository lotInfoRepository;
    private final LotInfoMapper lotInfoMapper;

    private static final String BUYER = "buyer";
    private static final String SELLER = "seller";
    private static final String LOT_STATUS = "lotStatus";
    private static final String DK = "dk";
    private static final String LOT_TOTAL_PRICE = "lotTotalPrice";
    private static final String PARTICIPANTS = "participants";
    private static final String LOT_URL = "lotURL";
    private static final String PDF_URL = "pdfURL";
    private static final String NAME = "name";

    public List<LotInfo> findAll() {
        return lotInfoRepository.findAll();
    }

    public Page<LotInfoResponseDTO> findByCriteria(LotInfoCriteriaRequestDto criteriaRequestDto, Pageable pageable) {
        Specification<LotInfo> specification = createSpecification(criteriaRequestDto);

        return lotInfoRepository.findAll(specification, pageable)
                .map(lotInfoMapper::toDto);
    }

    private Specification<LotInfo> createSpecification(LotInfoCriteriaRequestDto criteriaRequestDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteriaRequestDto.getBuyer() != null)
                predicates.add(criteriaBuilder.like(root.get(BUYER).get(NAME), "%" + criteriaRequestDto.getBuyer() + "%"));
            if (criteriaRequestDto.getSeller() != null)
                predicates.add(criteriaBuilder.like(root.get(SELLER).get(NAME), "%" + criteriaRequestDto.getSeller() + "%"));
            if (criteriaRequestDto.getLotStatus() != null)
                predicates.add(inOrEqualLotStatus(root, criteriaBuilder, LOT_STATUS, criteriaRequestDto.getLotStatus()));
            if (criteriaRequestDto.getDk() != null)
                predicates.add(inOrEqual(root, criteriaBuilder, DK, criteriaRequestDto.getDk()));
            if (criteriaRequestDto.getLotTotalPrice() != null)
                predicates.add(betweenOrEqual(root, criteriaBuilder, LOT_TOTAL_PRICE, criteriaRequestDto.getLotTotalPrice()));
            if (criteriaRequestDto.getParticipants() != null)
                predicates.add(inOrEqual(root, criteriaBuilder, PARTICIPANTS, criteriaRequestDto.getParticipants()));
            if (criteriaRequestDto.getLotURL() != null)
                predicates.add(criteriaBuilder.equal(root.get(LOT_URL), criteriaRequestDto.getLotURL()));
            if (criteriaRequestDto.getPdfURL() != null)
                predicates.add(criteriaBuilder.equal(root.get(PDF_URL), criteriaRequestDto.getPdfURL()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Predicate inOrEqual(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, String value) {
        String[] parsedValue = value.split(",");
        Predicate result;

        if (parsedValue.length == 1) {
            return criteriaBuilder.equal(root.get(column), value);
        }

        if (column.equals(PARTICIPANTS)) {
            result = root.get(column).get(NAME).in(Arrays.asList(parsedValue));
        } else {
            result = root.get(column).in(Arrays.asList(parsedValue));
        }

        return result;
    }

    private Predicate inOrEqualLotStatus(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, List<LotStatus> lotStatuses) {
        if (lotStatuses.size() == 1) {
            return criteriaBuilder.equal(root.get(column), lotStatuses.get(0));
        }

        return root.get(column).in(lotStatuses);
    }

    private Predicate betweenOrEqual(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, String value) {
        String[] parsedValue = value.split(",");

        if (parsedValue.length == 1) {
            return criteriaBuilder.equal(root.get(column), BigDecimal.valueOf(Double.valueOf(value)));
        }

        if (parsedValue.length != 2) {
            throw new RuntimeException();
        }

        return criteriaBuilder.between(root.get(column), BigDecimal.valueOf(Double.valueOf(parsedValue[0])),
                BigDecimal.valueOf(Double.valueOf(parsedValue[1])));
    }
}
