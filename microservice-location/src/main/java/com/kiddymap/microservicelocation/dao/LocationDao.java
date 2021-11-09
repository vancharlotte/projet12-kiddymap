package com.kiddymap.microservicelocation.dao;

import com.kiddymap.microservicelocation.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDao extends JpaRepository<Location, String> {

}
