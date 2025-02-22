package restaurant.manager.RestaurantWebServiceWithDatabase.Utilities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FoodQuantityConverter implements AttributeConverter<FoodQuantity, String> {
    @Override
    public String convertToDatabaseColumn(FoodQuantity attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public FoodQuantity convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(FoodQuantity.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
