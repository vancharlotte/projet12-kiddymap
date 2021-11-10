package com.kiddymap.microservicelocation.dao;

import com.kiddymap.microservicelocation.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, UUID> {

}
