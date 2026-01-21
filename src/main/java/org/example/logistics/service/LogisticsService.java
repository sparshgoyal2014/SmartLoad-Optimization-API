package org.example.logistics.service;


import org.antlr.v4.runtime.misc.Pair;
import org.example.logistics.Utility;
import org.example.logistics.data.OptimizedLoad;
import org.example.logistics.data.LoadInfo;
import org.example.logistics.data.Order;
import org.example.logistics.data.Truck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class LogisticsService {

    public OptimizedLoad optimizeTheLoad(LoadInfo loadInfo){
        //separate compatible orders
        // same Source, same destination, isHazmat
        //call optimizer method for each list of compatible orders
        // optimizer method contains all the DSA logic


        HashMap map = Utility.getCompatibleOrders(loadInfo.getOrders());

        final List<Pair<
                List<Order>,
                Pair<Integer, Integer>
                >> revenueFromEachKind = new ArrayList<>();

        map.forEach((key, value) -> revenueFromEachKind.add(new Pair<List<Order>, Pair<Integer, Integer>>((List<Order>)value, optimizeLoad(loadInfo.getTruck(), (List<Order>)value))));

        Pair<List<Order>, Pair<Integer, Integer>> bestRevenuePair = new Pair<List<Order>, Pair<Integer, Integer>>(null, new Pair<>(0,0));

        for(int i=0; i<revenueFromEachKind.size(); i++){
            if(revenueFromEachKind.get(i).b.b >= bestRevenuePair.b.b){
                bestRevenuePair = revenueFromEachKind.get(i);
            }
        }

        return buildOptimizedLoad(bestRevenuePair.b, loadInfo.getTruck(), bestRevenuePair.a, loadInfo.getOrders());

    }

    boolean isCompatible(Order a, Order b){
        return a.getPickup_date().before(b.getDelivery_date()) && b.getPickup_date().before(a.getDelivery_date());
    }


    Pair<Integer, Integer> optimizeLoad(Truck truck, List<Order> inputOrders) { // return pair of best mask and best revenue

        int n = inputOrders.size();
        int maxMask = 1 << n;

        boolean[][] compatible = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                compatible[i][j] = isCompatible(inputOrders.get(i), inputOrders.get(j));
            }
        }

        int[] dp = new int[maxMask];
        long[] weight = new long[maxMask];
        long[] volume = new long[maxMask];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        int bestRevenue = 0;
        int bestMask = 0;

        for (int mask = 1; mask < maxMask; mask++) {

            int lsb = mask & -mask;
            int i = Integer.numberOfTrailingZeros(lsb);
            int prev = mask ^ lsb;

            if (dp[prev] == -1) continue; // here prev is state


            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if ((prev & (1 << j)) != 0 && !compatible[i][j] && !compatible[j][i]) {
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;

            weight[mask] = weight[prev] + inputOrders.get(i).getWeight_lbs();
            volume[mask] = volume[prev] + inputOrders.get(i).getVolume_cuft();

            if (weight[mask] > truck.getMax_weight_lbs() || volume[mask] > truck.getMax_volume_cuft()) {
                continue;
            }

            dp[mask] = dp[prev] + inputOrders.get(i).getPayout_cents();

            if (dp[mask] > bestRevenue) {
                bestRevenue = dp[mask];
                bestMask = mask;
            }
        }


        Pair<Integer, Integer> p = new Pair<Integer, Integer>(bestMask, bestRevenue);

        return p;
    }

    public List<Order> selectOrdersFromMask(List<Order> inputOrders, int mask){
        List<Order> result = new ArrayList<>();
        int n = inputOrders.size();
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                result.add(inputOrders.get(i));
            }
        }

        return result;
    }

    public OptimizedLoad buildOptimizedLoad(Pair<Integer, Integer> bestRevenuePair, Truck truck, List<Order> inputOrderList, List<Order> allOrders){

        OptimizedLoad optimizedLoad = new OptimizedLoad();

        List<Order> orderList = new ArrayList<>();

        orderList = selectOrdersFromMask(inputOrderList, bestRevenuePair.a);

        int weightTaken = orderList.stream().mapToInt(order -> order.getWeight_lbs()).sum();
        int volumeTaken = orderList.stream().mapToInt(order -> order.getVolume_cuft()).sum();

        optimizedLoad.setTruck_id(truck.getId());
        optimizedLoad.setSelected_order_ids(orderList.stream().map(Order::getId).toList());
        optimizedLoad.setTotal_payout_cents(bestRevenuePair.b);
        optimizedLoad.setTotal_volume_cuft(volumeTaken);
        optimizedLoad.setTotal_weight_lbs(weightTaken);
        optimizedLoad.setUtilization_VOLUME_percent(((double)volumeTaken/truck.getMax_volume_cuft()) * 100.0);
        optimizedLoad.setUtilization_weight_percent(((double)weightTaken/truck.getMax_weight_lbs()) * 100.0);

        return optimizedLoad;

    }

}
