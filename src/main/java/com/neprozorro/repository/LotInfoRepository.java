package com.neprozorro.repository;

import com.neprozorro.model.LotInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface LotInfoRepository extends JpaRepository<LotInfo, UUID>, JpaSpecificationExecutor<LotInfo> {
}
