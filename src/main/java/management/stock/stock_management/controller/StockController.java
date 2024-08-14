package management.stock.stock_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import management.stock.stock_management.models.Stock;
import management.stock.stock_management.services.StockService;

@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockService stockServices;

    @GetMapping("/get-all-stocks")
    public List<Stock> getAllPosts() {
        return stockServices.getAllStocks();
    }

    @GetMapping("/get-single-stock/{id}")
    public Stock getSinglePost(@PathVariable int id) {
        return stockServices.getSingleStock(id);
    }

    @GetMapping("/get-user-stocks/{uid}")
    public List<Stock> getUserPosts(@PathVariable int uid) {
        return stockServices.getUserStocks(uid);
    }

    @PostMapping("/add-stock")
    public String addStock(@RequestBody Stock stock) {
        return stockServices.addStock(stock);
    }

    @DeleteMapping("/delete-stock/{id}")
    public String deleteStock(@PathVariable int id) {
        return stockServices.deleteStock(id);
    }

    @PutMapping("/update-stock/{id}")
    public String updateStock(@RequestBody Stock stock, @PathVariable int id) {
        return stockServices.updateStock(stock, id);
    }
}