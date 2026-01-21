package org.example.logistics.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class Order {
    @NotNull
    @NotBlank
    private String id;

    @Min(0)
    private int payout_cents;

    @Min(1)
    private int weight_lbs;

    @Min(1)
    private int volume_cuft;

    private String origin;
    private String destination;
    private Date pickup_date;
    private Date delivery_date;
    private boolean is_hazmat;

    public Order(String id, int payout_cents, int weight_lbs, int volume_cuft, String origin, String destination, Date pickup_date, Date delivery_date, boolean is_hazmat) {
        this.id = id;
        this.payout_cents = payout_cents;
        this.weight_lbs = weight_lbs;
        this.volume_cuft = volume_cuft;
        this.origin = origin;
        this.destination = destination;
        this.pickup_date = pickup_date;
        this.delivery_date = delivery_date;
        this.is_hazmat = is_hazmat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPayout_cents(int payout_cents) {
        this.payout_cents = payout_cents;
    }

    public void setWeight_lbs(int weight_lbs) {
        this.weight_lbs = weight_lbs;
    }

    public void setVolume_cuft(int volume_cuft) {
        this.volume_cuft = volume_cuft;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPickup_date(Date pickup_date) {
        this.pickup_date = pickup_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setIs_hazmat(boolean is_hazmat) {
        this.is_hazmat = is_hazmat;
    }

    public String getId() {
        return id;
    }

    public int getPayout_cents() {
        return payout_cents;
    }

    public int getWeight_lbs() {
        return weight_lbs;
    }

    public int getVolume_cuft() {
        return volume_cuft;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getPickup_date() {
        return pickup_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public boolean isIs_hazmat() {
        return is_hazmat;
    }


}
