package management.stock.stock_management.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "stock_db")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;

    private int uid;
    private String company_name;
    private int number;
    private String stock_description;
    private int stock_value;
    private int purchased_price;

}