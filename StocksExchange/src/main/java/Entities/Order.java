package Entities;

import java.time.LocalTime;

public class Order {
      private String orderId;
      private OrderType orderType;
      private Stock  stock;
      private LocalTime localTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", orderType=").append(orderType);
        sb.append(", stock=").append(stock);
        sb.append(", localTime=").append(localTime);
        sb.append('}');
        return sb.toString();
    }
}
