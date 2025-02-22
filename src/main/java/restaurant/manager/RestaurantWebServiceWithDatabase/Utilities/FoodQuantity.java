package restaurant.manager.RestaurantWebServiceWithDatabase.Utilities;

public enum FoodQuantity {
    VERY_LOW("VERY_LOW"), LOW("LOW"), MEDIUM("MEDIUM"), HIGH("HIGH"), VERY_HIGH("VERY_HIGH");

    private String code;

    private FoodQuantity(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}




