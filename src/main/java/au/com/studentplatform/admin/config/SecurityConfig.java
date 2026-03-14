package au.com.studentplatform.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import au.com.studentplatform.admin.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final CustomUserDetailsService userDetailsService;
    private final CustomAuthSuccessHandler successHandler;

    public SecurityConfig(CustomUserDetailsService userDetailsService, CustomAuthSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/app/login", "/app/register", "/app/register/user", "/css/**", "/js/**").permitAll()
                .requestMatchers("/app/admin/**").hasRole("ADMIN")
                .requestMatchers("/app/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            )
                .formLogin(form -> form
                	    .loginPage("/app/login")
                	    .usernameParameter("email")   // ✅ IMPORTANT
                	    .passwordParameter("password")
                	    //.defaultSuccessUrl("/app/student/dashboard", true)
                	    .defaultSuccessUrl("/app/login", true)
                	    .failureUrl("/app/login?error=true")
                	    .successHandler(successHandler) // ✅ HERE
                	    .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/app/logout")
                .logoutSuccessUrl("/app/login?logout=true")
                .invalidateHttpSession(true)
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
