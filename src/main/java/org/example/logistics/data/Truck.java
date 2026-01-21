package org.example.logistics.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Truck {

    @NotBlank(message = "truck_id cannot be blank")
    @NotNull(message = "truck_id cannot be null")
    private String id;

//    @Max(value = 100000, message = "value should be less than 100000")
    @Max(value = 100000)
    @Min(0)
    private int max_weight_lbs;


    @Max(100000)
    @Min(0)
    private int max_volume_cuft;

    public Truck(String id, int max_weight_lbs, int max_volume_cuft) {
        this.id = id;
        this.max_weight_lbs = max_weight_lbs;
        this.max_volume_cuft = max_volume_cuft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMax_weight_lbs() {
        return max_weight_lbs;
    }

    public void setMax_weight_lbs(int max_weight_lbs) {
        this.max_weight_lbs = max_weight_lbs;
    }

    public int getMax_volume_cuft() {
        return max_volume_cuft;
    }

    public void setMax_volume_cuft(int max_volume_cuft) {
        this.max_volume_cuft = max_volume_cuft;
    }
}
