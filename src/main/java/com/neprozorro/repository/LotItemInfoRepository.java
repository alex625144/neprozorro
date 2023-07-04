package com.neprozorro.repository;

import com.neprozorro.model.LotItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LotItemInfoRepository extends JpaRepository<LotItemInfo, UUID> {

}
