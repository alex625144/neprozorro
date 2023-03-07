package com.neprozorro.repository;

import com.neprozorro.model.LotPDFResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LotResultRepository extends JpaRepository<LotPDFResult, UUID> {
}
