package pl.czubak.charityapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.czubak.charityapp.entity.Role;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.RoleRepository;
import pl.czubak.charityapp.repository.UserRepository;

import java.util.ArrayList;
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRePassword(user.getPassword());
        user.setEnabled(1);
        Role role = roleRepository.findByRole("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setAdmin(true);
        userRepository.save(user);
    }
}
