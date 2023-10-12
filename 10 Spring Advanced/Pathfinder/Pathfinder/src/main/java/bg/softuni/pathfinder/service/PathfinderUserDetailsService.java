package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

public class PathfinderUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public PathfinderUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return this.userRepository.findByUsername(username)
               .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                       user.getRoles().stream()
                               .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                               .collect(Collectors.toList())
               )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
