package pl.skillmatrix.skillmatrixspringboot;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.skillmatrix.skillmatrixspringboot.service.AuthService;

@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthService authService() {
        return new AuthService();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/skillMatrix/home", true)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/skillMatrix").authenticated()
                .antMatchers("/skillMatrix/home").authenticated()
                .antMatchers("/skillMatrix/home/**").authenticated()
                .antMatchers("/skillMatrix/home/person").authenticated()
                .antMatchers("/skillMatrix/home/person/**").authenticated()
                .antMatchers("/skillMatrix/home/skills").authenticated()
                .antMatchers("/skillMatrix/home/skills/**").authenticated()
                .antMatchers("/skillMatrix/admin").hasAnyRole("AREA", "ADMIN")
                .antMatchers("/skillMatrix/admin/employee").hasAnyRole("AREA", "ADMIN")
                .antMatchers("/skillMatrix/admin/skills").hasAnyRole("AREA", "ADMIN")
                .antMatchers("/skillMatrix/admin/skills/**").hasAnyRole("AREA", "ADMIN")
                .antMatchers("/skillMatrix/admin/employee/**").hasAnyRole("AREA", "ADMIN")
                .antMatchers("/skillMatrix/admin/departments").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/departments/**").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/team").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/team/**").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/group").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/group/**").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/function/").hasRole("ADMIN")
                .antMatchers("/skillMatrix/admin/function/**").hasRole("ADMIN")
                .and()
                .csrf().disable();
    }
}
