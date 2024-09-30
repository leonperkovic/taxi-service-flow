package com.wearenotch.taxi.flow.delegate;

import com.wearenotch.taxi.flow.client.ApiClient;
import com.wearenotch.taxi.flow.client.dto.RideRequestDto;
import com.wearenotch.taxi.flow.client.dto.ScheduledRideDto;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class ReassignDriverDelegate implements JavaDelegate {
    private final ApiClient apiClient;

    public ReassignDriverDelegate(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var rideId = (String) execution.getVariable("rideId");

        var scheduledRide = apiClient.getRide(rideId);
        var availableDrivers = apiClient.estimateAvailableDrivers(toRideRequest(scheduledRide));
        if (availableDrivers.isEmpty()) throw new BpmnError("Error_NoAvailableDrivers");

        var newDriver = availableDrivers.getFirst();
        apiClient.assignDriver(rideId, newDriver.getId());

        execution.setVariable("driverId", newDriver.getId());
        execution.setVariable("rideResponseDueTime", toDate(scheduledRide.getPickupTime()));
    }

    private RideRequestDto toRideRequest(ScheduledRideDto rideDto) {
        var rideRequestDto = new RideRequestDto();
        rideRequestDto.setClientName(rideDto.getClientName());
        rideRequestDto.setPickupTime(rideDto.getPickupTime());
        rideRequestDto.setPickupLocation(rideDto.getPickupLocation());
        rideRequestDto.setDestination(rideDto.getDestination());
        return rideRequestDto;
    }

    private Date toDate(OffsetDateTime dateTime) {
        return Date.from(dateTime.toInstant());
    }
}
