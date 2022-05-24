//package pl.skillmatrix.skillmatrixspringboot.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pl.skillmatrix.skillmatrixspringboot.repository.UserRepository;
//
//import java.util.List;
//
////Here we don't have @Service bcs we have Bean in Security Config
//public class AuthService implements UserDetailsService {
//
//
//    //here we use Setter bcs that should help us with test without connect to db.
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         return userRepository.findByUsername(username)
//                 .map(user -> new User(
//                         user.getUsername(),
//                         user.getPassword(),
//                         List.of(new SimpleGrantedAuthority(user.getRoleList().toString()))))
//                 .orElseThrow( () -> new UsernameNotFoundException("not found"));
//    }
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//}
