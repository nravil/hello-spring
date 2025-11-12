package com.example.hellospring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    // Spring Data JPA САМ реализует эти методы!
    List<Calculation> findByOperation(String operation);
    List<Calculation> findByOrderByTimestampDesc();

    //Новые методы статистики
    //Сколько всего операций
    @Query("SELECT COUNT(c) FROM Calculation c")
    long getTotalOperations();

    // Количество операций по типам
@Query("SELECT c.operation, COUNT(c) FROM Calculation c GROUP BY c.operation")
    List<Object[]> getOperationsCount();
}
