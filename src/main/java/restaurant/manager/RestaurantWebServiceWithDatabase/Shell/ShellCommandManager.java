package restaurant.manager.RestaurantWebServiceWithDatabase.Shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommandManager {
    @ShellMethod(value = "Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}
