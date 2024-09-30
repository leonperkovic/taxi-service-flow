package com.wearenotch.taxi.flow.client.dto;

import java.time.OffsetDateTime;

public class RideRequestDto {
    private String clientName;
    private OffsetDateTime pickupTime;
    private String pickupLocation;
    private String destination;

    public RideRequestDto() {}

    public RideRequestDto(String clientName, OffsetDateTime pickupTime, String pickupLocation, String destination) {
        this.clientName = clientName;
        this.pickupTime = pickupTime;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
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

    @Override
    public String toString() {
        return "RideRequestDto{" +
               "clientName='" + clientName + '\'' +
               ", pickupTime=" + pickupTime +
               ", pickupLocation='" + pickupLocation + '\'' +
               ", destination='" + destination + '\'' +
               '}';
    }
}
