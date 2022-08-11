package vn.com.store.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import vn.com.store.security.CustomAuthenticationProvider;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login", "/register", "/forgot-password", "/dist/**","/plugins/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin().loginPage("/login").defaultSuccessUrl("/home")
                .failureUrl("/login?error=true").failureHandler(authenticationFailureHandler);

        http.rememberMe().tokenValiditySeconds(24 * 60 * 60 * 7)
                .userDetailsService(userDetailsService);

        http.logout().logoutUrl("/logout").clearAuthentication(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


}