package DAOLayer;

import Entities.Order;
import Entities.OrderFulfilled;
import Entities.OrderType;
import Utils.DoublyLinkedList;
import Utils.Node;

import java.util.HashMap;
import java.util.Map;

public class DBClient {
    private Map<String, Order> orderMap = new HashMap<>();
    private DoublyLinkedList<String> sellQueue = new DoublyLinkedList<String>();
    private DoublyLinkedList<String> buyQueue = new DoublyLinkedList<String>();

    private void addUpdateOrder(Order order) {
        String orderId = order.getOrderId();

        if (order.getStock().getQty() > 0) {
            this.orderMap.put(orderId, order);
        } else {
            if (this.orderMap.get(orderId) != null) {
                orderMap.remove(orderId);
            }
        }
    }

    public void addNewOrder(Order order) {
        addUpdateOrder(order);
        Node<String> node = new Node<>(order.getOrderId());
        if (order.getOrderType().equals(OrderType.SELL)) {
            sellQueue.add(node);
        } else
            buyQueue.add(node);

    }

    public OrderFulfilled matchSellOrder(Order order) {
        OrderFulfilled orderFulfilled = new OrderFulfilled();
        if (order.getStock().getQty() <= 0) {
            return orderFulfilled;
        }
        Integer fulfilled = 0;
        Double priceRequired = order.getStock().getPrice();
        Integer qtyRequired = order.getStock().getQty();
        Double priceUsed = 0.0;

        DoublyLinkedList<String> doublyLinkedList = null;
        doublyLinkedList = this.buyQueue;

        Node matching = null;
        Order od = null;
        Node start = doublyLinkedList.getStart();

        while (start != null) {
            od = orderMap.get(start.getItem());
            Double priceAvailable = od.getStock().getPrice();

            if (od.getStock().getName().equalsIgnoreCase(order.getStock().getName())) {
                if (priceAvailable >= priceRequired) {
                    matching = start;
                    priceUsed = priceRequired;
                    break;
                }

            }
            start = start.getNext();
        }

        orderFulfilled.setSellOrderId(order.getOrderId());

        if (matching != null) {
            Integer qtyAvail = od.getStock().getQty();
            if (qtyAvail <= qtyRequired) {
                fulfilled = qtyAvail;
            } else {
                fulfilled = qtyAvail - qtyRequired;
            }
            od.getStock().setQty(qtyAvail - fulfilled);
            addUpdateOrder(od);
            if (qtyAvail <= qtyRequired) {
                doublyLinkedList.delete(matching);
            }

            orderFulfilled.setQty(fulfilled);
            orderFulfilled.setPrice(priceUsed);
            orderFulfilled.setBuyOrderId(od.getOrderId());

        }
        return orderFulfilled;
    }

    public OrderFulfilled matchBuyOrder(Order order) {
        OrderFulfilled orderFulfilled = new OrderFulfilled();

        if (order.getStock().getQty() <= 0) {
            return orderFulfilled;
        }
        Integer fulfilled = 0;
        Double priceRequired = order.getStock().getPrice();
        Integer qtyRequired = order.getStock().getQty();
        Double priceUsed = 0.0;

        DoublyLinkedList<String> doublyLinkedList = null;
        doublyLinkedList = this.sellQueue;

        Node matching = null;
        Order od = null;
        Node start = doublyLinkedList.getStart();

        while (start != null) {
            od = orderMap.get(start.getItem());
            Double priceAvailable = od.getStock().getPrice();

            if (od.getStock().getName().equalsIgnoreCase(order.getStock().getName())) {
                if (priceAvailable <= priceRequired) {
                    matching = start;
                    if (od.getLocalTime().isAfter(order.getLocalTime())) {
                        priceUsed = priceRequired;
                    } else {
                        priceUsed = priceAvailable;
                    }
                    break;
                }

            }
            start = start.getNext();
        }
        orderFulfilled.setBuyOrderId(order.getOrderId());

        if (matching != null) {
            Integer qtyAvail = od.getStock().getQty();
            if (qtyAvail <= qtyRequired) {
                fulfilled = qtyAvail;
            } else {
                fulfilled = qtyAvail - qtyRequired;
            }
            od.getStock().setQty(qtyAvail - fulfilled);
            addUpdateOrder(od);
            if (qtyAvail <= qtyRequired) {
                doublyLinkedList.delete(matching);
            }

            orderFulfilled.setQty(fulfilled);
            orderFulfilled.setPrice(priceUsed);
            orderFulfilled.setSellOrderId(od.getOrderId());

        }
        return orderFulfilled;
    }
}
