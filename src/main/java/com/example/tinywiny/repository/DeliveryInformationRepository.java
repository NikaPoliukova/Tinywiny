package com.example.tinywiny.repository;

import com.example.tinywiny.model.DeliveryInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DeliveryInformationRepository extends JpaRepository<DeliveryInformation, Long> {

  DeliveryInformation findDeliveryInformationByDeliveryInformationId(@Param("id") Long id);
}
