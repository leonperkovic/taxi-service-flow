package com.wearenotch.taxi.flow.delegate;

import com.wearenotch.taxi.flow.client.ApiClient;
import com.wearenotch.taxi.flow.client.dto.RideRequestDto;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;

@Component
public class ScheduleRideDelegate implements JavaDelegate {
    private final ApiClient apiClient;

    public ScheduleRideDelegate(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var clientName = (String) execution.getVariable("clientName");
        var pickupTime = (String) execution.getVariable("pickupTime");
        var pickupLocation = (String) execution.getVariable("pickupLocation");
        var destination = (String) execution.getVariable("destination");

        var rideRequest = new RideRequestDto(clientName, toOffsetDateTime(pickupTime), pickupLocation, destination);
        var availableDrivers = apiClient.estimateAvailableDrivers(rideRequest);
        if (availableDrivers.isEmpty()) throw new BpmnError("Error_NoAvailableDrivers");

        var driver = availableDrivers.getFirst();
        var scheduledRide = apiClient.scheduleRide(driver.getId(), rideRequest);

        execution.setVariable("rideId", scheduledRide.getId());
        execution.setVariable("driverId", driver.getId());
        execution.setVariable("rideResponseDueTime", toDate(scheduledRide.getPickupTime().minusMinutes(15)));
        execution.setVariable("pickupDueTime", toDate(scheduledRide.getPickupTime()));
    }

    private OffsetDateTime toOffsetDateTime(String time) {
        var offsetTime = OffsetTime.parse(time);
        return offsetTime.atDate(LocalDate.now());
    }

    private Date toDate(OffsetDateTime dateTime) {
        return Date.from(dateTime.toInstant());
    }
}
