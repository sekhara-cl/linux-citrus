package citrusleaf.contact_form_submission.auth.config;

import citrusleaf.contact_form_submission.auth.AuthSuccessHandler;
import citrusleaf.contact_form_submission.auth.JsonObjectAuthenticationFilter;
import citrusleaf.contact_form_submission.auth.JwtAuthorizationFilter;
import citrusleaf.contact_form_submission.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final AuthSuccessHandler authSuccessHandler;
    private final CustomUserDetailsService jwtUserDetailsService;
    private final String secret;

    public SecurityConfig(AuthSuccessHandler authSuccessHandler, CustomUserDetailsService jwtUserDetailsService, @Value("${jwt.secret}") String secret) {
        this.authSuccessHandler = authSuccessHandler;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.secret = secret;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/user").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtUserDetailsService, secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return http.build();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

}

