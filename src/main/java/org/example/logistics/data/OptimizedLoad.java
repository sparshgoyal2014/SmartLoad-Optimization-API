package org.example.logistics.data;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class OptimizedLoad {

    private String truck_id;
    private List<String> order_ids;
    private int totalPayout;
    private int totalWeight;
    private int totalVolume;
    private double utilization_weight_percent;
    private double utilization_VOLUME_percent;

    public OptimizedLoad(){};

    public OptimizedLoad(String truck_id, List<String> order_ids, int totalPayout, int totalWeight, int totalVolume, double utilization_weight_percent, double utilization_VOLUME_percent) {
        this.truck_id = truck_id;
        this.order_ids = order_ids;
        this.totalPayout = totalPayout;
        this.totalWeight = totalWeight;
        this.totalVolume = totalVolume;
        this.utilization_weight_percent = utilization_weight_percent;
        this.utilization_VOLUME_percent = utilization_VOLUME_percent;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public List<String> getOrder_ids() {
        return order_ids;
    }

    public int getTotalPayout() {
        return totalPayout;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalVolume() {
        return totalVolume;
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

    public void setOrder_ids(List<String> order_ids) {
        this.order_ids = order_ids;
    }

    public void setTotalPayout(int totalPayout) {
        this.totalPayout = totalPayout;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public void setUtilization_weight_percent(double utilization_weight_percent) {
        this.utilization_weight_percent = utilization_weight_percent;
    }

    public void setUtilization_VOLUME_percent(double utilization_VOLUME_percent) {
        this.utilization_VOLUME_percent = utilization_VOLUME_percent;
    }
}
