package org.example.logistics.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class Order {
    @NotNull
    @NotBlank
    private String order_id;

    @Min(0)
    private int order_payout;

    @Min(1)
    private int weight;

    @Min(1)
    private int volume;

    private String origin;
    private String destination;
    private Date pickupDate;
    private Date dropDate;
    private boolean isHazmat;

    public Order(String order_id, int order_payout, int weight, int volume, String origin, String destination, Date pickupDate, Date dropDate, boolean isHazmat) {
        this.order_id = order_id;
        this.order_payout = order_payout;
        this.weight = weight;
        this.volume = volume;
        this.origin = origin;
        this.destination = destination;
        this.pickupDate = pickupDate;
        this.dropDate = dropDate;
        this.isHazmat = isHazmat;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_payout(int order_payout) {
        this.order_payout = order_payout;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setDropDate(Date dropDate) {
        this.dropDate = dropDate;
    }

    public void setHazmat(boolean hazmat) {
        isHazmat = hazmat;
    }

    public String getOrder_id() {
        return order_id;
    }

    public int getOrder_payout() {
        return order_payout;
    }

    public int getWeight() {
        return weight;
    }

    public int getVolume() {
        return volume;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public Date getDropDate() {
        return dropDate;
    }

    public boolean isHazmat() {
        return isHazmat;
    }


}
