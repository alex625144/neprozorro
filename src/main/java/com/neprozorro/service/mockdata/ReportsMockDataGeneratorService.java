package com.neprozorro.service.mockdata;

import com.neprozorro.model.ResultReport;
import com.neprozorro.model.ResultReportItem;
import com.neprozorro.repository.ResultReportItemRepository;
import com.neprozorro.repository.ResultReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportsMockDataGeneratorService {

    private final ResultReportRepository resultReportRepository;
    private final ResultReportItemRepository resultReportItemRepository;

    private static final int MOCK_DATA_NUMBER = 200;
    private static final int MAX_ITEMS_IN_REPORT = 10;
    private static final int MAX_LAPTOP_QUANTITY = 50;
    private static final int MONTHS = 6;
    private static final int DAYS = 29;
    private static final double MAX_PRICE_FOR_ITEM = 100_000.0;
    private static final String DK = "009023";
    private static final List<String> PREPARE_MOCK_MODEL_LIST = List.of(
            "Acer Aspire 7 A715-42G-R3EZ", "Dell Vostro 15 3501", "Xiaomi Mi RedmiBook 15",
            "Apple MacBook Air 13\" M1 256GB 2020", "Lenovo IdeaPad 3 15IAU7", "ASUS Laptop X515EA-BQ2066", "NOT_VALID_MODEL_NAME1",
            "Acer Aspire 3 A315-58G-548E", "ASUS TUF Gaming F15 FX506LHB-HN323", "Microsoft Surface Laptop 5",
            "Acer Aspire 3 A315-58G-3953", "HP Pavilion 15-eh2234nw", "Acer Nitro 5 AN515-57",
            "Lenovo IdeaPad L3 15ITL6", "Lenovo IdeaPad Gaming 3 15ACH6", "Asus ROG Strix G15 G513IC-HN092", "NOT_VALID_MODEL_NAME2",
            "Huawei MateBook 14S 14.2\"", "Samsung Galaxy Book 2 Pro", "Huawei MateBook D 16"
    );

    @Transactional
    public void generateResultReportsMockData(){
        for (int i = 0; i<MOCK_DATA_NUMBER; i++) {
            ResultReport resultReport = new ResultReport();
            ThreadLocalRandom randomNum = ThreadLocalRandom.current();
            BigDecimal lotPrice = BigDecimal.ZERO;
            int itemsQuantity = randomNum.nextInt(1, MAX_ITEMS_IN_REPORT);
            List<ResultReportItem> resultReportItems = new ArrayList<>(itemsQuantity);

            for (int j = 0; j < itemsQuantity; j++) {
                var resultReportItem = new ResultReportItem();
                resultReportItem.setModel(getModel(j));
                resultReportItem.setAmount(randomNum.nextInt(1, MAX_LAPTOP_QUANTITY));
                BigDecimal itemPrice = BigDecimal.valueOf(randomNum.nextDouble(1.0, MAX_PRICE_FOR_ITEM));
                resultReportItem.setItemPrice(itemPrice);
                BigDecimal marketPrice = itemPrice.subtract(BigDecimal.valueOf(itemPrice.doubleValue() * randomNum.nextDouble(-0.2, 0.2)));//????
                resultReportItem.setMarketPrice(marketPrice);
                resultReportItem.setPriceViolation(BigDecimal.valueOf((marketPrice.doubleValue()-itemPrice.doubleValue()) * resultReportItem.getAmount()));
                resultReportItem.setResultReport(resultReport);

                var totalItemPrice = itemPrice.multiply(BigDecimal.valueOf(resultReportItem.getAmount()));
                lotPrice = lotPrice.add(totalItemPrice);
                resultReportItems.add(resultReportItem);
                resultReportItemRepository.save(resultReportItem);
            }

            resultReport.setDk(DK);
            resultReport.setDate(randomDate());
            resultReport.setProzorroURL("https://prozorro.com/lot" + i);
            resultReport.setLotPrice(lotPrice);

            resultReportRepository.save(resultReport);
        }
    }

    private LocalDate randomDate() {
        ThreadLocalRandom randomNum = ThreadLocalRandom.current();

        return LocalDate.now()
                .minusMonths(randomNum.nextInt(0, MONTHS))
                .minusDays(randomNum.nextInt(0, DAYS));
    }

    private String getModel(int index) {
        if(index >= PREPARE_MOCK_MODEL_LIST.size()) {
            index %= PREPARE_MOCK_MODEL_LIST.size();
        }

        return PREPARE_MOCK_MODEL_LIST.get(index);
    }

    public void generateLotIngo() {
    }
}
