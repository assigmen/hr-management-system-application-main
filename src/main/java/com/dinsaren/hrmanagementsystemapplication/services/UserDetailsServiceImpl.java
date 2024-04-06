package com.dinsaren.hrmanagementsystemapplication.services;

import com.dinsaren.hrmanagementsystemapplication.models.User;
import com.dinsaren.hrmanagementsystemapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<User> username = userRepository.findByUsername(phone);
        if (username.isPresent()) {
            return UserDetailsImpl.build(username.get());
        }
        Optional<User> phoneNumber = userRepository.findByPhone(phone);
        if (phoneNumber.isPresent()) {
            return UserDetailsImpl.build(phoneNumber.get());
        }
        Optional<User> email = userRepository.findByEmail(phone);
        if (email.isPresent()) {
            return UserDetailsImpl.build(email.get());
        }
        throw new UsernameNotFoundException(phone);
    }
}
