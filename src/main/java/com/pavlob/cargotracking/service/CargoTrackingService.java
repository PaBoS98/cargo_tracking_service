package com.pavlob.cargotracking.service;

import com.pavlob.cargotracking.model.CargoModel;
import com.pavlob.cargotracking.model.DeliveryStatus;

import java.util.List;

public interface CargoTrackingService {

    CargoModel createCargo(CargoModel cargoDto);

    CargoModel updateCargo(CargoModel cargoDto);

    List<CargoModel> getAllCargo();

    CargoModel getCargo(long cargoId);

    List<CargoModel> getAllWhereStatusNot(DeliveryStatus deliveryStatus);
}
