package com.practice.first.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.model.Stock;

public interface StocksMySQLRepository extends JpaRepository<Stock, Long>{

}
