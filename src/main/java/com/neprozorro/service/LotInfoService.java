package com.neprozorro.service;

import com.neprozorro.model.LotInfo;
import com.neprozorro.model.LotStatus;
import com.neprozorro.repository.LotInfoRepository;
import com.neprozorro.rest.dto.LotInfoCriteriaRequestDto;
import com.neprozorro.rest.dto.LotInfoMapper;
import com.neprozorro.rest.dto.LotInfoResponseDTO;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String EDRPOU = "edrpou";

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
                predicates.add(likeOrEqual(root, criteriaBuilder, BUYER, criteriaRequestDto.getBuyer()));
            if (criteriaRequestDto.getSeller() != null)
                predicates.add(likeOrEqual(root, criteriaBuilder, SELLER, criteriaRequestDto.getSeller()));
            if (criteriaRequestDto.getLotStatus() != null)
                predicates.add(inOrEqualLotStatus(root, criteriaBuilder, LOT_STATUS, criteriaRequestDto.getLotStatus()));
            if (criteriaRequestDto.getDk() != null)
                predicates.add(inOrEqual(root, criteriaBuilder, DK, criteriaRequestDto.getDk()));
            if (criteriaRequestDto.getLotTotalPrice() != null)
                predicates.add(greaterThenOrBetween(root, criteriaBuilder, LOT_TOTAL_PRICE, criteriaRequestDto.getLotTotalPrice()));
            if (criteriaRequestDto.getParticipants() != null)
                predicates.add(inOrEqual(root, criteriaBuilder, PARTICIPANTS, criteriaRequestDto.getParticipants()));
            if (criteriaRequestDto.getLotURL() != null)
                predicates.add(criteriaBuilder.equal(root.get(LOT_URL), criteriaRequestDto.getLotURL()));
            if (criteriaRequestDto.getPdfURL() != null)
                predicates.add(criteriaBuilder.equal(root.get(PDF_URL), criteriaRequestDto.getPdfURL()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Predicate likeOrEqual(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, String value) {
        Pattern pattern = Pattern.compile("^\\s*\\d{8}\\s*$");
        Matcher matcher = pattern.matcher(value.trim());

        if (matcher.matches()) {
            return criteriaBuilder.equal(root.get(column).get(EDRPOU), value);
        }

        return criteriaBuilder.like(root.get(column).get(NAME), "%" + value + "%");
    }

    private Predicate inOrEqual(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, String value) {
        String[] parsedValue = value.split(",");
        Pattern pattern = Pattern.compile("^\\s*\\d{8}\\s*$");

        List<String> edrpous = new ArrayList<>();
        List<String> names = new ArrayList<>();

        Arrays.stream(parsedValue).forEach(e -> {
            Matcher matcher = pattern.matcher(e);
            if (matcher.matches()) {
                edrpous.add(e.trim());
            } else {
                names.add(e.trim());
            }
        });

        if (edrpous.isEmpty()) {
            return root.get(column).get(NAME).in(names);
        }
        if (names.isEmpty()) {
            return root.get(column).get(EDRPOU).in(edrpous);
        }

        return criteriaBuilder.or(root.get(column).get(EDRPOU).in(edrpous), root.get(column).get(NAME).in(names));
    }

    private Predicate inOrEqualLotStatus(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, List<LotStatus> lotStatuses) {
        if (lotStatuses.size() == 1) {
            return criteriaBuilder.equal(root.get(column), lotStatuses.get(0));
        }
        return root.get(column).in(lotStatuses);
    }

    private Predicate greaterThenOrBetween(Root<LotInfo> root, CriteriaBuilder criteriaBuilder, String column, String value) {

        String[] string = value.split(",");

        BigDecimal firstNumber = new BigDecimal(string[0].trim());
        BigDecimal secondNumber = new BigDecimal(string[1].trim());

        if (secondNumber.equals(BigDecimal.ZERO)) {
            return criteriaBuilder.greaterThan(root.get(column), firstNumber);
        }

        return criteriaBuilder.between(root.get(column), firstNumber, secondNumber);
    }
}
