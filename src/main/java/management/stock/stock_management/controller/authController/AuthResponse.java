package management.stock.stock_management.controller.authController;

public class AuthResponse {
    private String token;
    private String message;
    private int uid; // Add this field

    public AuthResponse(String token, String message, int uid) {
        this.token = token;
        this.message = message;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
