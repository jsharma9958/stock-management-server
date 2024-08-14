package management.stock.stock_management.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import management.stock.stock_management.models.Stock;

@Repository
public class StockDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Stock> getAllStock() {
        String query = "Select * from stock_db";
        List<Stock> StockList = null;
        StockList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Stock.class));
        return StockList;
    }

    public Stock getSingleStock(int sid) {
        String query = "Select * from stock_db where sid=?";
        Stock Stock = null;
        Stock = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Stock.class), sid);
        return Stock;
    }

    public List<Stock> getUserStocks(int uid) {
        String query = "SELECT * FROM stock_db s JOIN user_db u ON s.uid = u.uid WHERE u.uid = ?";
        List<Stock> userStockList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Stock.class), uid);
        return userStockList;
    }

    public String addStock(Stock Stock) {
        String query = "INSERT INTO stock_db(uid, company_name, number, stock_description, stock_value , purchased_price) VALUES (?, ?, ?, ?, ? ,?)";

        int status = jdbcTemplate.update(query, Stock.getUid(), Stock.getCompany_name(), Stock.getNumber(),
                Stock.getStock_description(), Stock.getStock_value(), Stock.getPurchased_price());

        if (status == 1) {
            return "Post added successfully";
        } else {
            return "Failed to add post";
        }

    }

    public String deleteStock(int sid) {
        String query = "Delete from stock_db where sid=? ";
        int status = jdbcTemplate.update(query, sid);
        if (status == 1) {
            return "Stock deleted Successfully";
        }
        return "Stock Deletion failed";
    }

    public String updateStock(Stock stock, int sid) {
        // Define the SQL query for updating a Stock
        String query = "UPDATE stock_db SET company_name = ?, number = ?, stock_description = ?, stock_value = ?, purchased_price = ? WHERE sid = ?";

        // Execute the update
        int status = jdbcTemplate.update(query,
                stock.getCompany_name(),
                stock.getNumber(),
                stock.getStock_description(),
                stock.getStock_value(),
                stock.getPurchased_price(),
                sid);

        // Return status message
        if (status == 1) {
            return "Stock updated successfully";
        } else {
            return "Failed to update Stock";
        }
    }
}