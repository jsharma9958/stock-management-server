package management.stock.stock_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import management.stock.stock_management.dao.StockDao;
import management.stock.stock_management.models.Stock;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao sDao;

    @Override
    public List<Stock> getAllStocks() {
        return sDao.getAllStock();
    }

    @Override
    public Stock getSingleStock(int sid) {
        return sDao.getSingleStock(sid);
    }

    @Override
    public List<Stock> getUserStocks(int uid) {
        return sDao.getUserStocks(uid);
    }

    @Override
    public String addStock(Stock stock) {
        return sDao.addStock(stock);
    }

    @Override
    public String deleteStock(int sid) {
        return sDao.deleteStock(sid);
    }

    @Override
    public String updateStock(Stock stock, int sid) {
        return sDao.updateStock(stock, sid);
    }

}
