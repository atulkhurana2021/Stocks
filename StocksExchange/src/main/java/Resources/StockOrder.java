package Resources;

import DAOLayer.DBClient;
import Models.OrderType;
import Models.StockOrderRequest;
import ResourceImpl.StockOrderImpl;

public class StockOrder {
    private DBClient dbClient;
    private StockOrderImpl stockOrderImpl;

    public StockOrder(DBClient dbClient) {
        this.dbClient = dbClient;
        this.stockOrderImpl = new StockOrderImpl(dbClient);
    }

    public void placeOrder(StockOrderRequest stockOrderRequest) throws Exception {
        if (stockOrderRequest.getOrderType().equals(OrderType.SELL)) {
            stockOrderImpl.sellOrder(stockOrderRequest);
        } else {
            stockOrderImpl.buyOrder(stockOrderRequest);
        }

    }
}
