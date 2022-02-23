package com.kiddymap.microservicelocation.dao;

import com.kiddymap.microservicelocation.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttributeDao extends JpaRepository<Attribute, UUID> {
}
