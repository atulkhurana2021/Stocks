package Utils;

import Entities.OrderFulfilled;

public class Utils {
    public static void printOrderFulfilled(OrderFulfilled orderFulfilled) {
        System.out.println(orderFulfilled.getBuyOrderId()+ " "+orderFulfilled.getPrice()+" "+ orderFulfilled.getQty() + " "+orderFulfilled.getSellOrderId());

    }
}
