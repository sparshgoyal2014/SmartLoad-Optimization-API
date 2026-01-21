package org.example.logistics.data;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class OptimizedLoad {

    private String truck_id;
    private List<String> selected_order_ids;
    private int total_payout_cents;
    private int total_weight_lbs;
    private int total_volume_cuft;
    private double utilization_weight_percent;
    private double utilization_VOLUME_percent;

    public OptimizedLoad(){};

    public OptimizedLoad(String truck_id, List<String> selected_order_ids, int total_payout_cents, int total_weight_lbs, int total_volume_cuft, double utilization_weight_percent, double utilization_VOLUME_percent) {
        this.truck_id = truck_id;
        this.selected_order_ids = selected_order_ids;
        this.total_payout_cents = total_payout_cents;
        this.total_weight_lbs = total_weight_lbs;
        this.total_volume_cuft = total_volume_cuft;
        this.utilization_weight_percent = utilization_weight_percent;
        this.utilization_VOLUME_percent = utilization_VOLUME_percent;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public List<String> getSelected_order_ids() {
        return selected_order_ids;
    }

    public int getTotal_payout_cents() {
        return total_payout_cents;
    }

    public int getTotal_weight_lbs() {
        return total_weight_lbs;
    }

    public int getTotal_volume_cuft() {
        return total_volume_cuft;
    }

    public double getUtilization_weight_percent() {
        return utilization_weight_percent;
    }

    public double getUtilization_VOLUME_percent() {
        return utilization_VOLUME_percent;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public void setSelected_order_ids(List<String> selected_order_ids) {
        this.selected_order_ids = selected_order_ids;
    }

    public void setTotal_payout_cents(int total_payout_cents) {
        this.total_payout_cents = total_payout_cents;
    }

    public void setTotal_weight_lbs(int total_weight_lbs) {
        this.total_weight_lbs = total_weight_lbs;
    }

    public void setTotal_volume_cuft(int total_volume_cuft) {
        this.total_volume_cuft = total_volume_cuft;
    }

    public void setUtilization_weight_percent(double utilization_weight_percent) {
        this.utilization_weight_percent = utilization_weight_percent;
    }

    public void setUtilization_VOLUME_percent(double utilization_VOLUME_percent) {
        this.utilization_VOLUME_percent = utilization_VOLUME_percent;
    }
}
