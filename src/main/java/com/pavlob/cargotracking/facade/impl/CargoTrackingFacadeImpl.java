package com.pavlob.cargotracking.facade.impl;

import com.pavlob.cargotracking.dto.DeliveryStatusDto;
import com.pavlob.cargotracking.facade.CargoTrackingFacade;
import com.pavlob.cargotracking.dto.CargoDto;
import com.pavlob.cargotracking.model.CargoModel;
import com.pavlob.cargotracking.model.DeliveryStatus;
import com.pavlob.cargotracking.service.impl.CargoTrackingServiceImpl;
import com.pavlob.mapper.CargoDtoModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CargoTrackingFacadeImpl implements CargoTrackingFacade {

    @Autowired
    private CargoTrackingServiceImpl cargoTrackingService;

    @Override
    public CargoDto createCargo(final CargoDto cargoDto) {
        final CargoModel cargo = CargoDtoModelMapper.INSTANCE.cargoDtoToCargoModel(cargoDto);

        return CargoDtoModelMapper.INSTANCE.cargoModelToCargoDto(cargoTrackingService.createCargo(cargo));
    }

    @Override
    public CargoDto updateCargo(final CargoDto cargoDto) {
        final CargoModel cargo = CargoDtoModelMapper.INSTANCE.cargoDtoToCargoModel(cargoDto);

        return CargoDtoModelMapper.INSTANCE.cargoModelToCargoDto(cargoTrackingService.updateCargo(cargo));
    }

    @Override
    public List<CargoDto> getAllCargo() {
        final List<CargoModel> cargoModels = cargoTrackingService.getAllCargo();

        return cargoModels.stream()
                .map( CargoDtoModelMapper.INSTANCE::cargoModelToCargoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CargoDto> getAllWhereStatusNot(final DeliveryStatusDto deliveryStatus) {
        final List<CargoModel> cargoModels = cargoTrackingService.getAllWhereStatusNot(DeliveryStatus.valueOf(deliveryStatus.name()));
        return cargoModels.stream()
                .map( CargoDtoModelMapper.INSTANCE::cargoModelToCargoDto)
                .collect(Collectors.toList());
    }
}
