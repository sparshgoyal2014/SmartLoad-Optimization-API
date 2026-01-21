package org.example.logistics.data;

import jakarta.persistence.Entity;
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
    private List<Order> ordersList;

    public LoadInfo(Truck truck, List<Order> ordersList) {
        this.truck = truck;
        this.ordersList = ordersList;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }
}


