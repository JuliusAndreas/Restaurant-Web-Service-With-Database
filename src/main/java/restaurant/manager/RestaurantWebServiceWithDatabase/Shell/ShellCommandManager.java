package restaurant.manager.RestaurantWebServiceWithDatabase.Shell;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import restaurant.manager.RestaurantWebServiceWithDatabase.Redis.RestaurantCacheManager;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandManager {

    private final RestaurantCacheManager restaurantCacheManager;

    @ShellMethod(value = "Add two integers together.")
    public int ad(int a, int b) {
        return a + b;
    }

    @Scheduled(cron = "${intervalInCron.fetchRestaurantsFromDBtoCache}")
    @ShellMethod(value = "Fill the restaurants cache with data from database")
    public String fill() {
        restaurantCacheManager.fillTheCache();
        return "Successfully filled the cache";
    }

    @Scheduled(cron = "${intervalInCron.fetchRestaurantsFromDBtoCache}")
    @ShellMethod(value = "Flush the restaurants cache")
    public String flush() {
        restaurantCacheManager.flushCache();
        return "Successfully flushed the cache";
    }
}
