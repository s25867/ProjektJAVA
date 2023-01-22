package com.example.projektJAVA.repository;

import com.example.projektJAVA.model.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmenRepository extends JpaRepository<Salesman, Long> {
}
