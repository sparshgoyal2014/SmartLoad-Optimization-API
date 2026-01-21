package org.example.logistics.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Truck {

    @NotBlank(message = "truck_id cannot be blank")
    @NotNull(message = "truck_id cannot be null")
    private String truck_id;

//    @Max(value = 100000, message = "value should be less than 100000")
    @Max(value = 100000)
    @Min(0)
    private int maxWeight;


    @Max(100000)
    @Min(0)
    private int maxVolume;

    public Truck(String truck_id, int maxWeight, int maxVolume) {
        this.truck_id = truck_id;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }
}
