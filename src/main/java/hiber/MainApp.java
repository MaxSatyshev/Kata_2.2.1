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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      Car car = new Car("bmw", 500);
      Car car2 = new Car("lexus", 600);
      Car car3 = new Car("lada", 7);

      userService.add(new User("User5", "Lastname5", "user5@mail.ru", car));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", car3));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru", car3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getEmpCar() == null) {
            System.out.println("Машиной не владеет.");
         } else {
            System.out.println("Car = " + user.getEmpCar());
         }
         System.out.println();
      }

      System.out.println("*****************************************************");
      List<User> result = userService.userFromCar("lada", 7);

      if (!result.isEmpty()) {
         result.stream().forEach(user -> System.out.println(user.toString()));
      }

      context.close();
   }
}
