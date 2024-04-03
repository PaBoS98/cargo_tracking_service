package com.pavlob.cargotracking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CargoDto {

    private String id;

    private String name;

    private String deliveryCity;

    private String cargoLocation;

    private DeliveryStatusDto deliveryStatus;

    private Date createdDate;

    private Date modifiedDate;

    @Override
    public String toString() {
        return "CargoDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", cargoLocation='" + cargoLocation + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
