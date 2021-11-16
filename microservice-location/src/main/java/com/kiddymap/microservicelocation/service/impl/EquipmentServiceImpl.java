package com.kiddymap.microservicelocation.service.impl;

import com.kiddymap.microservicelocation.dao.EquipmentDao;
import com.kiddymap.microservicelocation.dao.LocationDao;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.contrat.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentDao equipmentDao;

    @Override
    public Equipment saveEquipment(Equipment equipment) {
        return equipmentDao.save(equipment) ;
    }

    @Override
    public Optional<Equipment> getEquipment(UUID id) {
        return equipmentDao.findById(id);
    }

    @Override
    public Iterable<Equipment> getAllEquipments() {
        return equipmentDao.findAll();
    }

    @Override
    public void deleteEquipment(UUID id) {
        equipmentDao.deleteById(id);
    }


}
