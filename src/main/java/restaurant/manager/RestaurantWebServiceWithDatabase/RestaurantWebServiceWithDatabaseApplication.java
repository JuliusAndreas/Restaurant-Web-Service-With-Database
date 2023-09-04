package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.Product;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling
public class RestaurantWebServiceWithDatabaseApplication implements CommandLineRunner {

    public static final Integer MAX_THREADS = 2;
    @Autowired
    RedissonClient client;
    List<Product> productList;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productList = List.of(new Product(1, "a", client),
                new Product(2, "b", client),
                new Product(3, "c", client));
        Random random = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < MAX_THREADS; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        productList.get(random.nextInt(3)).readModifyWrite();
                        System.out.println(productList);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
    }
}
