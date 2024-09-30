package com.wearenotch.taxi.flow.client.dto;

public class TaxiDriverDto {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaxiDriverDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
