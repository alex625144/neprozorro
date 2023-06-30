package com.neprozorro.service.mockdata;

import com.neprozorro.model.LotInfo;
import com.neprozorro.model.LotItemInfo;
import com.neprozorro.model.LotStatus;
import com.neprozorro.model.Participant;
import com.neprozorro.repository.LotInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class LotInfoMockDataGeneratorService {

    private static final int MAX_LAPTOP_QUANTITY = 50;
    private static final int MAX_PRICE_FOR_ITEM = 100_000;
    private final LotInfoRepository lotInfoRepository;
    private final ParticipantMockDataSource participantMockDataSource;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final int LOT_INFO_MOCK_QUANTITY = 1_000;
    private static final int MAX_LOT_INFO_ITEMS_QUANTITY = 10;
    private static final int MAX_PARTICIPANTS = 10;
    private static final String DK = "009023";
    private static final String LOT_URL = "https://gov.e-tender.ua/";
    private static final String PDF_URL = "https://gov.e-tender.ua/pdf/";
    private static final List<String> PREPARE_MOCK_MODEL_LIST = List.of(
            "Acer Aspire 7 A715-42G-R3EZ", "Dell Vostro 15 3501", "Xiaomi Mi RedmiBook 15",
            "Apple MacBook Air 13\" M1 256GB 2020", "Lenovo IdeaPad 3 15IAU7", "ASUS Laptop X515EA-BQ2066", "NOT_VALID_MODEL_NAME1",
            "Acer Aspire 3 A315-58G-548E", "ASUS TUF Gaming F15 FX506LHB-HN323", "Microsoft Surface Laptop 5",
            "Acer Aspire 3 A315-58G-3953", "HP Pavilion 15-eh2234nw", "Acer Nitro 5 AN515-57",
            "Lenovo IdeaPad L3 15ITL6", "Lenovo IdeaPad Gaming 3 15ACH6", "Asus ROG Strix G15 G513IC-HN092", "NOT_VALID_MODEL_NAME2",
            "Huawei MateBook 14S 14.2\"", "Samsung Galaxy Book 2 Pro", "Huawei MateBook D 16"
    );

    public void generateLotInfoMockData() {
        List<LotInfo> lotInfoList = new ArrayList<>();
        
        for(int i = 0; i < LOT_INFO_MOCK_QUANTITY; i++) {
            LotInfo lotInfo = new LotInfo();

            lotInfo.setBuyer(participantMockDataSource.getNextParticipant());
            lotInfo.setSeller(participantMockDataSource.getNextParticipant());
            lotInfo.setLotStatus(LotStatus.values()[random.nextInt(0, LotStatus.values().length)]);
            lotInfo.setDk(DK);
            lotInfo.setParticipants(getMockParticipants());
            lotInfo.setLotURL(LOT_URL+i);
            lotInfo.setPdfURL(PDF_URL+i);
            lotInfo.setLotItems(getMockLotItemInfos(lotInfo));
            lotInfo.setLotTotalPrice(countTotalPrice(lotInfo.getLotItems()));

            lotInfoList.add(lotInfo);
        }

        lotInfoRepository.saveAll(lotInfoList);
    }

    private BigDecimal countTotalPrice(List<LotItemInfo> lotItems) {
        var totalPrice = BigDecimal.ZERO;

        for (LotItemInfo item : lotItems) {
            totalPrice = totalPrice.add(item.getTotalItemPrice());
        }

        return totalPrice;
    }

    private List<LotItemInfo> getMockLotItemInfos(LotInfo lotInfo) {
        int itemsQuantity = random.nextInt(1, MAX_LOT_INFO_ITEMS_QUANTITY);
        List<LotItemInfo> lotItemInfos = new ArrayList<>(itemsQuantity);

        for (int i = 0; i < itemsQuantity; i++) {
            LotItemInfo lotItemInfo = new LotItemInfo();
            lotItemInfo.setModel(getModel(i));
            BigDecimal itemPrice = BigDecimal.valueOf(random.nextDouble(1.0, MAX_PRICE_FOR_ITEM));
            lotItemInfo.setPrice(itemPrice);
            lotItemInfo.setAmount(random.nextInt(1, MAX_LAPTOP_QUANTITY));
            var totalItemPrice = itemPrice.multiply(BigDecimal.valueOf(lotItemInfo.getAmount()));
            lotItemInfo.setTotalItemPrice(totalItemPrice);
            lotItemInfo.setLotInfo(lotInfo);

            lotItemInfos.add(lotItemInfo);
        }

        return lotItemInfos;
    }

    private List<Participant> getMockParticipants() {
        int participantsQuantity = random.nextInt(0, MAX_PARTICIPANTS);
        List<Participant> participants = new ArrayList<>(participantsQuantity);

        for (int i = 0; i < participantsQuantity; i++) {
            participants.add(participantMockDataSource.getNextParticipant());
        }

        return participants;
    }

    private String getModel(int index) {
        if(index >= PREPARE_MOCK_MODEL_LIST.size()) {
            index %= PREPARE_MOCK_MODEL_LIST.size();
        }

        return PREPARE_MOCK_MODEL_LIST.get(index);
    }
}
