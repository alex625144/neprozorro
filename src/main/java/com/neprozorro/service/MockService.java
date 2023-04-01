package com.neprozorro.service;

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
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MockService {

    private final ResultReportRepository resultReportRepository;
    private final ResultReportItemRepository resultReportItemRepository;

    @Transactional
    public void save(){ //TODO for remove
        final int MOCK_DATA_NUMBER = 30;

        for (int i = 0; i<MOCK_DATA_NUMBER;i ++) {
            Random randomNum = new Random();
            int priceRandom = randomNum.nextInt(100);
            log.info("price random = " + priceRandom);

            int dateRandom = randomNum.nextInt(1, 30);
            log.info("date random = " + dateRandom);

            int itemRandom = randomNum.nextInt(1, 5);
            log.info("item random = " + itemRandom);

            ResultReport report = new ResultReport();
            report.setDk("1234234");
            report.setDate(LocalDate.of(2023, 03, dateRandom));
            report.setLotPrice(new BigDecimal(priceRandom));
            report.setProzorroURL("new url");
            report.setTotalAmount(1);

            ResultReport saved = resultReportRepository.save(report);

            for(int j = 0; j<itemRandom; j++) {
                ResultReportItem item = new ResultReportItem();
                item.setAmount(j);
                item.setMarketPrice(new BigDecimal(priceRandom));
                item.setModel("Lenovo");
                item.setPriceViolation(new BigDecimal(j));
                item.setResultReport(saved);
                resultReportItemRepository.save(item);
            }
        }
    }
}
