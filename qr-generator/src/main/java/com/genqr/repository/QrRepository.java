package com.genqr.repository;

import com.genqr.model1.QrEntity; // Asegúrate de que termine en model1
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends JpaRepository<QrEntity, Long> {
}