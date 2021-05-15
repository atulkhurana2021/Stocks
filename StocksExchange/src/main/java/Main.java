import DAOLayer.DBClient;
import Models.StockOrderRequest;
import Resources.StockOrder;
import Utils.RequestConverter;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        DBClient dbClient = new DBClient();

        StockOrder stockOrder = new StockOrder(dbClient);

        File file = new File(args[0]);

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String inp = sc.nextLine();
            StockOrderRequest stockOrderRequest = RequestConverter.convertStockOrderString(inp);
            stockOrder.placeOrder(stockOrderRequest);
        }

    }
}
