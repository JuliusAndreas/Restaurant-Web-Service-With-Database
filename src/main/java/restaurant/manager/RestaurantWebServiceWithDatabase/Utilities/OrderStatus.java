package restaurant.manager.RestaurantWebServiceWithDatabase.Utilities;

public enum OrderStatus {
    DONE("DONE"), ONGOING("ONGOING");

    private String code;

    private OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
