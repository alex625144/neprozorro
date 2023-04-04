package com.neprozorro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "result_report_item")
public class ResultReportItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "Id")
    private UUID id;

    @Column(name = "model")
    private String model;

    @Column(name = "amount")
    private int amount;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "market_price")
    private BigDecimal marketPrice;

    @Column(name = "price_violation")
    private BigDecimal priceViolation;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ResultReport.id", referencedColumnName = "id")
    private ResultReport resultReport;
}