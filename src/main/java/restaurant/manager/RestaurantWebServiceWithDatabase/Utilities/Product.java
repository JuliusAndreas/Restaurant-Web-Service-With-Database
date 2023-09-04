package restaurant.manager.RestaurantWebServiceWithDatabase.Utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@Data
@AllArgsConstructor
public class Product {

    private Integer id;
    private String serial;
    private RedissonClient client;

    public void readModifyWrite() {
        RLock lock = client.getLock("lock " + id);
        lock.lock();
        id++;
        serial = serial.concat("a");
        lock.unlock();
    }
}
