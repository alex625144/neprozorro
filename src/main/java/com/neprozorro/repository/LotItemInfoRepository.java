package com.neprozorro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LotItemInfoRepository extends JpaRepository<com.neprozorro.model.LotItemInfo, UUID> {

}
