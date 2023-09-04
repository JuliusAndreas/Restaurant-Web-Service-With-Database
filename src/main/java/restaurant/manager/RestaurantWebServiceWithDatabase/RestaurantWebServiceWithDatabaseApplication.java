package restaurant.manager.RestaurantWebServiceWithDatabase;

import org.redisson.api.*;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import restaurant.manager.RestaurantWebServiceWithDatabase.Entities.Food;
import restaurant.manager.RestaurantWebServiceWithDatabase.Redis.CacheConfig;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.FoodQuantity;

@SpringBootApplication
public class RestaurantWebServiceWithDatabaseApplication {

    @Value("${singleServerConfig.address}")
    String address;
    @Autowired
    RedissonClient client;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebServiceWithDatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return runner -> {
            // Sync-Async //
            RAtomicLongAsync longObject = client.getAtomicLong("myLong");
            longObject.setAsync(1);
            RFuture<Long> future = longObject.getAsync();
            future.whenComplete((res, exception) -> {
//				System.out.println(res);
            });

            //objects//
            RBucket<Food> bucket = client.getBucket("Ash reshteh");
            bucket.set(new Food("Ash reshteh", FoodQuantity.VERY_HIGH, 10.0, 20.5));
//            System.out.println(bucket.get());

            //AtomicLong//
            RAtomicLong atomicLong = client.getAtomicLong("myAtomicLong");
            atomicLong.set(5);
//            System.out.println(atomicLong.incrementAndGet());
//            System.out.println(atomicLong.incrementAndGet());

            //Topic//

//            RTopic subscribeTopic = client.getTopic("baeldung");
//            subscribeTopic.addListener(CustomMessage.class, (channel, customMessage) -> {
//                RFuture<CustomMessage> messageRFuture;
//
//
//            });
//
//            RTopic publishTopic = client.getTopic("baeldung");
//            long clientsReceivedMessage
//                    = publishTopic.publish(new CustomMessage("This is a message"));

            RAtomicLong atomicLong2 = client.getAtomicLong("myAtomicLong");
            atomicLong.set(8);
            RTopic topic = client.getTopic("topic2");
            topic.addListener(String.class, (channel, msg) -> atomicLong2.set(3));

            Long receivers = topic.publish("msg");
            Thread.sleep(500);
//            System.out.println("clients received message: " + receivers + "\n" + atomicLong2.get());

            // Collections //
            // Maps //
//            RMap<String, Food> map = client.getMap("restaurant");
//            Food food = map.put("Gheymeh", new Food("Gheymeh", FoodQuantity.HIGH,
//                    9.5, 10.5));
//            System.out.println(map.get("Gheymeh"));

            // Set //
//            RSet<Food> ledgerSet = client.getSet("restaurant");
//            ledgerSet.add(new Food("Steak", FoodQuantity.VERY_HIGH, 10.0, 14.5));
//            ledgerSet.add(new Food("Stak", FoodQuantity.VERY_HIGH, 10.0, 14.5));

//            System.out.println(client.getSet("Steak"));

            // List //
//            RList<Food> ledgerList = client.getList("restaurant");
//            ledgerList.add(new Food());

            // Locks //
            RLock lock = client.getLock("test-lock");

            // Testing //
            System.out.println(client.getList(CacheConfig.GLOBAL_RESTAURANTS_NAME));

        };
    }

}
