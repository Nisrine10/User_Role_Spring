package ma.enset.service;

import ma.enset.entities.Role;
import ma.enset.entities.User;
import ma.enset.repositories.UserRepository;
import org.springframework.stereotype.Service;


public interface UserService {

    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User autehticate(String userName,String password);

}
