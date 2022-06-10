package pl.skillmatrix.skillmatrixspringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skillmatrix.skillmatrixspringboot.model.User;
import pl.skillmatrix.skillmatrixspringboot.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthService authService;

        @Modifying
        @Transactional
    public void saveUser(User user) {
            userRepository.save(User.builder().
                    username(user.getUsername()).
                    password(passwordEncoder.encode(user.getPassword())).
                    enabled(false).
                    role("ROLE_User").build());
        }

        @Transactional
        @Modifying
        public void modifyEnabledUser(Integer userID) {
            User user = userRepository.findUserById(userID);
            if(user.isEnabled() == false) {
                user.setEnabled(true);
            }else{
                user.setEnabled(false);
            }
            userRepository.save(user);
        }

    @Transactional
    @Modifying
    public void modifyRoleUser(Integer userID, String role) {
        User user = userRepository.findUserById(userID);
        user.setRole(role);
        userRepository.save(user);
    }

}

