package ResourceImpl;

import DAOLayer.DBClient;
import Entities.Order;
import Entities.OrderFulfilled;
import Models.StockOrderRequest;
import Utils.RequestConverter;
import Utils.Utils;

public class StockOrderImpl {
    private DBClient dbClient;

    public StockOrderImpl(DBClient dbClient) {
        this.dbClient = dbClient;
    }

    public void buyOrder(StockOrderRequest stockOrderRequest) throws Exception {
        Order order = RequestConverter.getOrderFromRequest(stockOrderRequest);

        OrderFulfilled orderFulfilled = dbClient.matchBuyOrder(order);
        while (orderFulfilled.getQty() > 0 ) {
            Utils.printOrderFulfilled(orderFulfilled);
            order.getStock().setQty(order.getStock().getQty() - orderFulfilled.getQty());
            orderFulfilled = dbClient.matchBuyOrder(order);
        }
        if (order.getStock().getQty() > 0) {
            dbClient.addNewOrder(order);
        }
    }

    public void sellOrder(StockOrderRequest stockOrderRequest) throws Exception {
        Order order = RequestConverter.getOrderFromRequest(stockOrderRequest);

        OrderFulfilled orderFulfilled = dbClient.matchSellOrder(order);
        while (orderFulfilled.getQty() > 0 ) {
            Utils.printOrderFulfilled(orderFulfilled);
            order.getStock().setQty(order.getStock().getQty() - orderFulfilled.getQty());
            orderFulfilled = dbClient.matchSellOrder(order);
        }
        if (order.getStock().getQty() > 0) {
            dbClient.addNewOrder(order);
        }
    }

}
