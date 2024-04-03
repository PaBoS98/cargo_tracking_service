package com.pavlob.cargotracking.api;

import com.pavlob.cargotracking.dto.CargoDto;
import com.pavlob.cargotracking.dto.DeliveryStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/api/cargoReporting")
public interface CargoTrackingApi {

    @GetMapping()
    List<CargoDto> getAllCargo();

    @GetMapping("/notInStatus")
    List<CargoDto> getAllCargoWhereStatusNot(@RequestParam("deliveryStatus") DeliveryStatusDto deliveryStatus);
}
