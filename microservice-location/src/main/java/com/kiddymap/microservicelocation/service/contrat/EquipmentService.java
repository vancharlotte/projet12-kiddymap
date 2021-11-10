package com.kiddymap.microservicelocation.service.contrat;

import com.kiddymap.microservicelocation.model.Equipment;

import java.util.Optional;
import java.util.UUID;

public interface EquipmentService {

    Equipment saveEquipment(Equipment equipment);

    Optional<Equipment> getEquipment(UUID id);

    Iterable<Equipment> getAllEquipments();

    void deleteEquipment(UUID id);
}
