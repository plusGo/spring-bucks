package com.mhl.springbucks.com.mhl.repository;

import com.mhl.springbucks.com.mhl.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
