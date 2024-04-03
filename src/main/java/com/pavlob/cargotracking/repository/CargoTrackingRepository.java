package com.pavlob.cargotracking.repository;

import com.pavlob.cargotracking.model.CargoModel;
import com.pavlob.cargotracking.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoTrackingRepository extends JpaRepository<CargoModel, Long> {

    List<CargoModel> findByDeliveryStatusNot(DeliveryStatus status);
}
