package ma.enset;
import ma.enset.entities.Role;
import ma.enset.entities.User;
import ma.enset.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEnsetApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return  args -> {
            User user1=new User();
            user1.setUserName("user1");
            user1.setPassword("123456");
            userService.addNewUser(user1);

            User user2=new User();
            user2.setUserName("admin");
            user2.setPassword("123456");
            userService.addNewUser(user2);

            Stream.of("STUDENT","USER","ADMIN").forEach(role->{
                Role role1=new Role();
                role1.setRoleName(role);

                userService.addNewRole(role1);
            });

            userService.addRoleToUser("user1","STUDENT");
            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("admin","ADMIN");

            try{
                User user=userService.autehticate("user1","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUserName());
                System.out.println("Roles => ");
                user.getRoles().forEach(r-> System.out.println("Role=> " +r.toString()));
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        };
    }

}
