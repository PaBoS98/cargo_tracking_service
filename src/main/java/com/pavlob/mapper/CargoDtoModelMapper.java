package com.pavlob.mapper;

import com.pavlob.cargotracking.dto.CargoDto;
import com.pavlob.cargotracking.model.CargoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CargoDtoModelMapper {

    CargoDtoModelMapper INSTANCE = Mappers.getMapper(CargoDtoModelMapper.class);

    CargoDto cargoModelToCargoDto(CargoModel cargo);

    CargoModel cargoDtoToCargoModel(CargoDto cargoDto);
}
