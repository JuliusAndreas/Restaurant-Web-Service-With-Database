package restaurant.manager.RestaurantWebServiceWithDatabase.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter authFilter;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService();
    }

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/users/").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/users/").hasAuthority("BOSS")
                .requestMatchers(HttpMethod.GET,"/users/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.PUT,"/users/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.DELETE,"/users/{id}").hasAuthority("BOSS")
                .requestMatchers("/restaurants/").hasAuthority("EATER")
                .requestMatchers(HttpMethod.GET, "/restaurants/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.POST, "/restaurants/").hasAuthority("BOSS")
                .requestMatchers(HttpMethod.PUT, "/restaurants/{id}").hasAuthority("BOSS")
                .requestMatchers(HttpMethod.DELETE, "/restaurants/{id}").hasAuthority("BOSS")
                .requestMatchers(HttpMethod.GET, "/restaurants/foods/{restaurantId}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.POST, "/restaurants/foods/{restaurantId}").hasAuthority("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/restaurants/foods/{restaurantId}/{foodId}").hasAuthority("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/restaurants/foods/{restaurantId}/{foodId}").hasAuthority("MANAGER")
                .requestMatchers("/reservations/restaurant/{id}").hasAuthority("MANAGER")
                .requestMatchers("/reservations/user/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.POST, "/reservations/").hasAuthority("EATER")
                .requestMatchers(HttpMethod.GET, "/reservations/").hasAuthority("BOSS")
                .requestMatchers(HttpMethod.GET, "/reservations/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.PUT, "/reservations/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.DELETE, "/reservations/{id}").hasAuthority("EATER")
                .requestMatchers(HttpMethod.PATCH, "/reservations/{id}").hasAuthority("MANAGER")
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}