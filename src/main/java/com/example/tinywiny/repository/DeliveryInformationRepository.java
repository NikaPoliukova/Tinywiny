package com.example.tinywiny.repository;

import com.example.tinywiny.model.DeliveryInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryInformationRepository extends JpaRepository<DeliveryInformation, Long> {

  DeliveryInformation findDeliveryInformationByDeliveryInformationId(@Param("id") Long id);
}
