package lt.codeacademy.service;

import lt.codeacademy.entity.Role;
import lt.codeacademy.entity.User;
import lt.codeacademy.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s does not exist", username)));
    }

    public void createNewUser(User registration) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User(registration.getUsername(),
                registration.getName(),
                registration.getSurname(),
                registration.getCountry(),
                registration.getCity(),
                registration.getStreet(),
                registration.getZipCode(),
                registration.getPhoneNumber(),
                encoder.encode(registration.getPassword()),
                Set.of(new Role(UUID.fromString("777e0319-0f1c-43cf-b1dc-62ce282d7765"), "USER'")));
        userRepository.save(user);
    }
}
