package com.springsecurity.aprendendoSecurity.service;

import com.springsecurity.aprendendoSecurity.entity.UserEntity;
import com.springsecurity.aprendendoSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        return (UserDetails) user;
    }

    public UserEntity cadastrar(UserEntity user){
        UserEntity userNovo = new UserEntity();

        userNovo.setUsername(user.getUsername());
        userNovo.setPassword(psEncode().encode(user.getPassword()));
        userNovo.setRole(user.getRole());

        return userNovo;
    }

    static PasswordEncoder psEncode(){
        return new BCryptPasswordEncoder();
    }
}
