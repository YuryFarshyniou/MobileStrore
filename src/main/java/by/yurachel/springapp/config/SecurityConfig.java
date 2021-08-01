package by.yurachel.springapp.config;

import by.yurachel.springapp.config.security.handlers.CustomAccessDeniedHandler;
import by.yurachel.springapp.model.user.Permission;
import by.yurachel.springapp.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/phones", "/phones/{^[\\d]$}", "/registration").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/phones/new"
                        , "/phones/{\\d+}/updatePhone").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/phones/{^[\\d]$}").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .antMatchers("/users", "/users/new").hasRole(Role.ADMIN.name())
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .and()
                .rememberMe().userDetailsService(userDetailsService).tokenValiditySeconds(2592000);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

}
