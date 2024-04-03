package com.pavlob.cargotracking.api.impl;

import com.pavlob.cargotracking.api.CargoTrackingApi;
import com.pavlob.cargotracking.dto.CargoDto;
import com.pavlob.cargotracking.dto.DeliveryStatusDto;
import com.pavlob.cargotracking.facade.CargoTrackingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CargoTrackingApiImpl implements CargoTrackingApi {

    @Autowired
    private CargoTrackingFacade cargoTrackingFacade;

    @Override
    public List<CargoDto> getAllCargo() {
        return cargoTrackingFacade.getAllCargo();
    }

    @Override
    public List<CargoDto> getAllCargoWhereStatusNot(DeliveryStatusDto deliveryStatus) {
        return cargoTrackingFacade.getAllWhereStatusNot(deliveryStatus);
    }
}
