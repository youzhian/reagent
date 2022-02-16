package com.ylv.modules.stock.repository;

import com.ylv.modules.stock.bean.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDetailRepository extends JpaRepository<StockDetail,Long> {
}
