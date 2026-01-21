package org.example.logistics;

import org.antlr.v4.runtime.misc.Triple;
import org.example.logistics.data.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utility {

    public static HashMap<Triple<String, String, Boolean>, List<Order>> getCompatibleOrders(List<Order> orders){

        HashMap<Triple<String, String, Boolean>, List<Order>> map = new HashMap();

        for(Order order: orders){
            String source = order.getOrigin();
            String destination = order.getDestination();
            boolean isHazmat = order.isIs_hazmat();

            Triple<String, String, Boolean> key = new Triple(source, destination, isHazmat);

            if(map.containsKey(key)){
                map.get(key).add(order);
            }else{
                map.putIfAbsent(key, new ArrayList<Order>());
                map.get(key).add(order);
            }
        }

        return map;
    }

}
