package management.stock.stock_management.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "user_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String name;
    private String email;
    private String password;
    private String gender;

}
