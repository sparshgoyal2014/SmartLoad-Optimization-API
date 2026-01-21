package org.example.logistics.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class LoadInfo {


    @Valid
    @NotNull
    private Truck truck;

    @Valid
    @NotNull
    @NotEmpty
    private List<Order> orders;

    public LoadInfo(Truck truck, List<Order> orders) {
        this.truck = truck;
        this.orders = orders;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


