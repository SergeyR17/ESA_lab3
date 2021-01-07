package com.example.company_lab3.repository;

import com.example.company_lab3.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Интерфейс к нашей базе данных средствами Spring (JpaRepository)
@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {
}
