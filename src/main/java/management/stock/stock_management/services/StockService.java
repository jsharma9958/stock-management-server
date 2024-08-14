package management.stock.stock_management.services;

import java.util.List;

import management.stock.stock_management.models.Stock;

public interface StockService {
    List<Stock> getAllStocks();

    Stock getSingleStock(int sid);

    List<Stock> getUserStocks(int uid);

    String addStock(Stock stock);

    String deleteStock(int sid);

    String updateStock(Stock stock, int sid);
}
