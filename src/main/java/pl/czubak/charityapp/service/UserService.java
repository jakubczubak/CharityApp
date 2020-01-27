package pl.czubak.charityapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.czubak.charityapp.entity.Role;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.RoleRepository;
import pl.czubak.charityapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    public UserService(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public void saveUser(User user){
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRePassword(encodePassword);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setEnabled(1);
        user.setAdmin(true);
        userRepository.save(user);
    }

    public void updateUser(User user){
        User userBeforeUpdate = userRepository.findById(user.getId()).get();
        user.setEnabled(userBeforeUpdate.getEnabled());
        user.setAdmin(userBeforeUpdate.isAdmin());
        user.setRePassword(userBeforeUpdate.getRePassword());
        user.setPassword(userBeforeUpdate.getPassword());
        user.setRoles(userBeforeUpdate.getRoles());

        userRepository.save(user);
    }
}
