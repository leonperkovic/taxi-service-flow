package com.wearenotch.taxi.flow.client.dto;

public class AssignDriverDto {
    private String driverId;

    public AssignDriverDto() {}
    
    public AssignDriverDto(String driverId) {
        this.driverId = driverId;
    }
    
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "AssignDriverDto{" +
               "driverId='" + driverId + '\'' +
               '}';
    }
}
