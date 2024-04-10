package com.pavlob.cargotracking.facade;

import com.pavlob.cargotracking.dto.CargoDto;
import com.pavlob.cargotracking.dto.DeliveryStatusDto;

import java.util.List;

public interface CargoTrackingFacade {

    CargoDto createCargo(CargoDto cargoDto);

    CargoDto updateCargo(CargoDto cargoDto);

    List<CargoDto> getAllCargo();

    CargoDto getCargo(long cargoId);

    List<CargoDto> getAllWhereStatusNot(DeliveryStatusDto deliveryStatus);
}
