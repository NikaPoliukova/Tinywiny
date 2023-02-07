package com.example.tinywiny.repository;

import com.example.tinywiny.model.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {


}
