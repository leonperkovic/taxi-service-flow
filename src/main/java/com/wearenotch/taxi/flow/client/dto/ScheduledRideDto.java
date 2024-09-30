package com.wearenotch.taxi.flow.client.dto;

import java.time.OffsetDateTime;

public class ScheduledRideDto {
    private String id;
    private String clientName;
    private OffsetDateTime pickupTime;
    private String pickupLocation;
    private String destination;
    private String driverId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public OffsetDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(OffsetDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "ScheduledRideDto{" +
               "id='" + id + '\'' +
               ", clientName='" + clientName + '\'' +
               ", pickupTime=" + pickupTime +
               ", pickupLocation='" + pickupLocation + '\'' +
               ", destination='" + destination + '\'' +
               ", driverId='" + driverId + '\'' +
               '}';
    }
}
