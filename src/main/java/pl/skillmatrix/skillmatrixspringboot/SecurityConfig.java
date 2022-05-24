//package pl.skillmatrix.skillmatrixspringboot;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(null).passwordEncoder(passwordEncoder());
//    }
//
//
//    //    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/skillMatrix/**").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/skillMatrix/admin/**").hasRole("ADMIN")
////                .antMatchers("/skillMatrix/admin/**").hasAnyAuthority("ROLE_ADMIN")
////                .and().formLogin()
////                .loginPage("/skillMatrix")
////                .defaultSuccessUrl("/skillMatrix");
////    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//}
