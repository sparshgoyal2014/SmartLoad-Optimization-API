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


        System.out.println("Api HIT SUCCESFUL");

        System.out.println(loadInfo);

        HashMap map = Utility.getCompatibleOrders(loadInfo.getOrdersList());

        final List<Pair<
                List<Order>,
                Pair<Integer, Integer>
                >> revenueFromEachKind = new ArrayList<>();

        map.forEach((key, value) -> {
            System.out.println("key is: ");
            System.out.println(key);
            System.out.println("================");
            System.out.println("value is:");

            for(Order order : (List<Order>)value){
                System.out.println(order.getOrder_id());
            }


            revenueFromEachKind.add(new Pair<List<Order>, Pair<Integer, Integer>>((List<Order>)value, optimizeLoad(loadInfo.getTruck(), (List<Order>)value)));

        });

        Pair<List<Order>, Pair<Integer, Integer>> bestRevenuePair = new Pair<List<Order>, Pair<Integer, Integer>>(null, new Pair<>(0,0));

        for(int i=0; i<revenueFromEachKind.size(); i++){
            if(revenueFromEachKind.get(i).b.b >= bestRevenuePair.b.b){
                bestRevenuePair = revenueFromEachKind.get(i);
            }
        }


        return buildOptimizedLoad(bestRevenuePair.b, loadInfo.getTruck(), bestRevenuePair.a, loadInfo.getOrdersList());

    }

    boolean overlap(Order a, Order b){
        return !a.getPickupDate().after(b.getPickupDate()) && !b.getDropDate().after(a.getDropDate());
    }

    boolean overlap2(Order a, Order b){
        return a.getPickupDate().before(b.getDropDate()) && b.getPickupDate().before(a.getDropDate());
    }


    Pair<Integer, Integer> optimizeLoad(Truck truck, List<Order> inputOrders) { // return pair of best mask and best revenue

        int n = inputOrders.size();
        int maxMask = 1 << n;

        boolean[][] compatible = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                compatible[i][j] = overlap2(inputOrders.get(i), inputOrders.get(j));
            }
        }


        printCompatibilityMatrix(compatible);

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

            if(mask == 1){
                System.out.print("Current mask is: ");
                System.out.print(mask);
                System.out.println();
                System.out.print("Current lsb is: ");
                System.out.print(lsb);
                System.out.println();
                System.out.print("Current noOftrailing zeros is: ");
                System.out.print(i);


                System.out.print("current prev is: ");
                System.out.println(prev);


                System.out.println("===============================");
            }



            if (dp[prev] == -1) continue; // here prev is state

            // Check time compatibility
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if ((prev & (1 << j)) != 0 && !compatible[i][j] && !compatible[j][i]) {
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;

            weight[mask] = weight[prev] + inputOrders.get(i).getWeight();
            volume[mask] = volume[prev] + inputOrders.get(i).getVolume();

            if (weight[mask] > truck.getMaxWeight() || volume[mask] > truck.getMaxVolume()) {
                continue;
            }


            dp[mask] = dp[prev] + inputOrders.get(i).getOrder_payout();

            if (dp[mask] > bestRevenue) {
                bestRevenue = dp[mask];
                bestMask = mask;
            }
        }


        Pair<Integer, Integer> p = new Pair<Integer, Integer>(bestMask, bestRevenue);


        System.out.println("Printing dp");

        printDp(dp);

        System.out.println("best mask: ");
        System.out.println(bestMask);
        System.out.println(bestRevenue);


        return p;
    }


    public void printDp(int[] dp){
        int size = dp.length;

        for(int i=0; i<size; i++){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    public void printCompatibilityMatrix(boolean[][] comp){
        int size = comp.length;
        int rowSize = comp[0].length;

        for(int i=0; i<size; i++){
            for(int j=0; j<rowSize; j++){
                System.out.println(i + " : " + j + " = " + comp[i][j]);
            }
        }
    }

    public List<Order> selectOrdersFromMask(List<Order> inputOrders, int mask){
        // Reconstruct selected orders
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

        int totalWeightOfAllOrders = allOrders.stream().mapToInt(order -> order.getWeight()).sum();
        int totalVolumeOfAllOrders = allOrders.stream().mapToInt(order -> order.getVolume()).sum();

        int weightTaken = orderList.stream().mapToInt(order -> order.getWeight()).sum();
        int volumeTaken = orderList.stream().mapToInt(order -> order.getVolume()).sum();

        optimizedLoad.setTruck_id(truck.getTruck_id());
        optimizedLoad.setOrder_ids(orderList.stream().map(Order::getOrder_id).toList());
        optimizedLoad.setTotalPayout(bestRevenuePair.b);
        optimizedLoad.setTotalVolume(volumeTaken);
        optimizedLoad.setTotalWeight(weightTaken);
        optimizedLoad.setUtilization_VOLUME_percent(((double)volumeTaken/totalVolumeOfAllOrders) * 100.0);
        optimizedLoad.setUtilization_weight_percent(((double)weightTaken/totalWeightOfAllOrders) * 100.0);


        return optimizedLoad;


    }

}
