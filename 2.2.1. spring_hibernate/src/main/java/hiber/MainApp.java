package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Mazda", 1);
        Car car2 = new Car("BMW", 2);
        Car car3 = new Car("Subaru", 3);
        Car car4 = new Car("Honda", 4);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCar(car1);
        car1.setUser(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(car2);
        car2.setUser(user2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCar(car3);
        car3.setUser(user3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setCar(car4);
        car4.setUser(user4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("Найденный пользователь:\n");
        User foundUser = userService.getUserByCar(car1.getModel(), car1.getSeries());
        System.out.println(foundUser);

        context.close();
    }
}
