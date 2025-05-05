package aula.microservicos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/seguranca/admin").authenticated()
                .requestMatchers("/seguranca/info", "/h2-console", "/seguranca/user").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        return http.build();

    }

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsService(PasswordEncoder
     * passwordEncoder) {
     * 
     * UserDetails user = User.withUsername("teste")
     * .password("123")
     * .roles("USER")
     * .build();
     * 
     * UserDetails admin = User.withUsername("admin")
     * .password("{noop}admin")
     * .roles("USER", "ADMIN")
     * .build();
     * 
     * UserDetails admin2 = User.withUsername("admin2")
     * .password("$2a$12$1.piNlB1kyC42TY2r70cPuirTHzFj3gknm7Ex6iTVfPQFV3DtNzhO")
     * .roles("USER", "ADMIN")
     * .build();
     * 
     * return new InMemoryUserDetailsManager(user, admin, admin2);
     * 
     * }
     */

    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource datasource) {

        return new JdbcUserDetailsManager(datasource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

}
