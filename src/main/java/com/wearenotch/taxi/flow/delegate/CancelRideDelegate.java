package com.wearenotch.taxi.flow.delegate;

import com.wearenotch.taxi.flow.client.ApiClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CancelRideDelegate implements JavaDelegate {
    private final ApiClient apiClient;

    public CancelRideDelegate(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        var rideId = (String) execution.getVariable("rideId");
        apiClient.cancelRide(rideId);
    }
}
