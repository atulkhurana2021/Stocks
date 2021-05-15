package Utils;

import Entities.Order;
import Entities.Stock;
import Models.OrderType;
import Models.StockOrderRequest;

import java.time.LocalTime;

public class RequestConverter {

    public static StockOrderRequest convertStockOrderString(String order) {

        StockOrderRequest stockOrderRequest = new StockOrderRequest();
        String arr[] = order.split(" ");
        stockOrderRequest.setOrderId(arr[0]);
        LocalTime time = LocalTime.parse(arr[1], Constants.parseFormat);
        stockOrderRequest.setTime(time);
        stockOrderRequest.setStock(arr[2]);
        stockOrderRequest.setOrderType(OrderType.valueOf(arr[3].toUpperCase()));
        stockOrderRequest.setPrice(Double.parseDouble(arr[4]));
        stockOrderRequest.setQty(Integer.parseInt(arr[5]));
        return stockOrderRequest;
    }

    public static Order getOrderFromRequest(StockOrderRequest stockOrderRequest) {

        Stock stock = new Stock();
        stock.setName(stockOrderRequest.getStock());
        stock.setPrice(stockOrderRequest.getPrice());
        stock.setQty(stockOrderRequest.getQty());

        Order order = new Order();
        order.setStock(stock);
        order.setOrderId(stockOrderRequest.getOrderId());
        order.setLocalTime(stockOrderRequest.getTime());
        order.setOrderType(Entities.OrderType.valueOf(stockOrderRequest.getOrderType().name()));
        return order;
    }

}
