package com.example.company_lab3.repository;

import com.example.company_lab3.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.util.List;
import java.util.Optional;
// Интерфейс к нашей базе данных средствами Spring (JpaRepository)
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query("select t from Worker t where t.personnelNumber = :number")
    Optional<Worker> findByPersonnelNumber(@Param("number") int number);

}