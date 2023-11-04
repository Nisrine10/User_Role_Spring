package ma.enset.repositories;

import ma.enset.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,String> {
   User findByUserName(String userName);
}
