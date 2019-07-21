package com.task.notes.core.services.user.impl;

import com.task.notes.api.facade.security.model.UserPrincipal;
import com.task.notes.data.entities.User;
import com.task.notes.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        final User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserPrincipal(user.getId(), user.getEmail(), user.getPasswordHash());
    }
}
