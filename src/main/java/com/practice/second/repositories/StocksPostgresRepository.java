package com.practice.second.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.model.Stock;

public interface StocksPostgresRepository extends JpaRepository<Stock, Long>{

}
