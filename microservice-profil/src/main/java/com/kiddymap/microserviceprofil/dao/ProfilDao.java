package com.kiddymap.microserviceprofil.dao;

import com.kiddymap.microserviceprofil.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilDao extends JpaRepository<Profil, String> {




}
