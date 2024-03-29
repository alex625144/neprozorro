package com.neprozorro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "result_report")
public class ResultReport {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "dk")
    private String dk;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "prozorro_url")
    private String prozorroURL;

    @Column(name = "lot_price")
    private BigDecimal lotPrice;

    @Column(name = "total_amount")
    private int totalAmount;

    @JsonBackReference
    @OneToMany(mappedBy = "resultReport", cascade = CascadeType.ALL)
    List<ResultReportItem> items;

    @Column(name = "total_price_violation")
    private BigDecimal totalPriceViolation;
}