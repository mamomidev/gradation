package org.hh99.gradation.security;

import org.hh99.gradation.domain.entity.User;
import org.hh99.gradation.message.ErrorMessage;
import org.hh99.gradation.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(ErrorMessage.EXIST_USER_ERROR_MESSAGE.getErrorMessage());
        }

        return new UserDetailsImpl(user);
    }
}