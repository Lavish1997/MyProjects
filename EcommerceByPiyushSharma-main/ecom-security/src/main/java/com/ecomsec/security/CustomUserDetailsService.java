package com.ecomsec.security;

import com.ecomsec.entity.Role;
import com.ecomsec.entity.User;
import com.ecomsec.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    //This method will automatically get the username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userRepository.findByUsernameOrEmail(username,username);

        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));


    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
