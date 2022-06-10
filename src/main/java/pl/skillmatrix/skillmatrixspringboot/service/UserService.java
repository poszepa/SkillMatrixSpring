package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.User;
import pl.skillmatrix.skillmatrixspringboot.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Modifying
        @Transactional
    public void saveUser(User user) {
            userRepository.save(User.builder().
                    username(user.getUsername()).
                    password(passwordEncoder.encode(user.getPassword())).
                    enabled(false).
                    role("ROLE_User").build());
        }
    }

