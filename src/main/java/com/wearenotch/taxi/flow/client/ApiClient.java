package com.wearenotch.taxi.flow.client;

import com.wearenotch.taxi.flow.client.dto.AssignDriverDto;
import com.wearenotch.taxi.flow.client.dto.RideRequestDto;
import com.wearenotch.taxi.flow.client.dto.ScheduledRideDto;
import com.wearenotch.taxi.flow.client.dto.TaxiDriverDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ApiClient {
    private final WebClient taxiServiceWebClient;

    public ApiClient(WebClient taxiServiceWebClient) {
        this.taxiServiceWebClient = taxiServiceWebClient;
    }

    public List<TaxiDriverDto> estimateAvailableDrivers(RideRequestDto rideRequestDto) {
        return taxiServiceWebClient.post()
                .uri("/drivers/estimate-available")
                .bodyValue(rideRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TaxiDriverDto>>() {})
                .block();
    }

    public ScheduledRideDto scheduleRide(String driverId, RideRequestDto rideRequestDto) {
        return taxiServiceWebClient.post()
                .uri("/drivers/{id}/schedule-ride", driverId)
                .bodyValue(rideRequestDto)
                .retrieve()
                .bodyToMono(ScheduledRideDto.class)
                .block();
    }

    public ScheduledRideDto getRide(String rideId) {
        return taxiServiceWebClient.get()
                .uri("/rides/{id}", rideId)
                .retrieve()
                .bodyToMono(ScheduledRideDto.class)
                .block();
    }

    public void cancelRide(String rideId) {
        taxiServiceWebClient.post()
                .uri("/rides/{id}/cancel", rideId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void assignDriver(String rideId, String driverId) {
        taxiServiceWebClient.post()
                .uri("/rides/{id}/assign", rideId)
                .bodyValue(new AssignDriverDto(driverId))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
