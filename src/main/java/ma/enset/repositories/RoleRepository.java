package ma.enset.repositories;

import ma.enset.entities.Role;
import ma.enset.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);


}
