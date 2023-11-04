package ma.enset.service;

import jakarta.transaction.Transactional;
import ma.enset.entities.Role;
import ma.enset.entities.User;
import ma.enset.repositories.RoleRepository;
import ma.enset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user=findUserByUserName(userName);
        Role role=findRoleByRoleName(roleName);
        if(user.getRoles()!=null){
            //Bidirectinnelle
            user.getRoles().add(role);
             role.getUsers().add(user);
        }

        //userRepository.save(user);
    }

    @Override
    public User autehticate(String userName, String password) {
        User user=userRepository.findByUserName(userName);
        if(user==null) throw  new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return  user;
        }
        throw  new RuntimeException("Bad credentials");
    }
}
