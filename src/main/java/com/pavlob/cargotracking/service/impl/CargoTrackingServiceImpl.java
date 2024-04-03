package com.pavlob.cargotracking.service.impl;

import com.pavlob.cargotracking.model.CargoModel;
import com.pavlob.cargotracking.model.DeliveryStatus;
import com.pavlob.cargotracking.repository.CargoTrackingRepository;
import com.pavlob.cargotracking.service.CargoTrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CargoTrackingServiceImpl implements CargoTrackingService {

    @Autowired
    private CargoTrackingRepository cargoTrackingRepository;

    @Override
    @Transactional
    public CargoModel createCargo(final CargoModel cargo) {
        log.info("save cargo report {}", cargo);
        return cargoTrackingRepository.save(cargo);
    }

    @Override
    @Transactional
    public CargoModel updateCargo(final CargoModel cargo) {
        log.info("update cargo report {}", cargo);
        return cargoTrackingRepository.save(cargo);
    }

    @Override
    @Transactional
    public List<CargoModel> getAllCargo() {
        return cargoTrackingRepository.findAll();
    }

    @Override
    @Transactional
    public List<CargoModel> getAllWhereStatusNot(final DeliveryStatus deliveryStatus) {
        return cargoTrackingRepository.findByDeliveryStatusNot(deliveryStatus);
    }
}
