package pl.czubak.charityapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.czubak.charityapp.entity.Role;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.RoleRepository;
import pl.czubak.charityapp.repository.UserRepository;

import java.util.*;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUser(User user) {
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRePassword(encodePassword);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setEnabled(1);
        user.setAdmin(false);
        userRepository.save(user);
    }

    public void saveAdmin(User admin) {
        String encodePassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        admin.setRePassword(encodePassword);
        Role adminRole = roleRepository.findByRole("ADMIN");
        admin.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
        admin.setEnabled(1);
        admin.setAdmin(true);
        userRepository.save(admin);
    }

    public void updateUser(User user) {
        User userBeforeUpdate = userRepository.findById(user.getId()).get();
        user.setEnabled(userBeforeUpdate.getEnabled());
        user.setAdmin(userBeforeUpdate.isAdmin());
        user.setRePassword(userBeforeUpdate.getRePassword());
        user.setPassword(userBeforeUpdate.getPassword());
        user.setRoles(userBeforeUpdate.getRoles());

        userRepository.save(user);
    }


}
