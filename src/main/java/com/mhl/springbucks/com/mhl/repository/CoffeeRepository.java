package com.mhl.springbucks.com.mhl.repository;

import com.mhl.springbucks.com.mhl.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
