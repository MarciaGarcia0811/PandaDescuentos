package com.pandadescuentos.spring.security;

import com.pandadescuentos.spring.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    //@Autowired
    private MyUserDetailsService userDetailsService;

    //Los Bean son instancias u objetos que son administrados por el núcleo de spring
    //Se pone a nivel de los métodos para inidicarle que va a ser un Bean
    @Bean
    public static PasswordEncoder passwordEncoder() {
        //Retornamos una instancia del PasswordEncoder para ser manejada dentro del nucleo del proyecto
        return new BCryptPasswordEncoder();
    }
    //método que retorna la cadena de filtros de seguridad
    //Acá definimos rutas a las que damos permiso y rutas a las que prohibimos
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Al objeto HttpSecurity le indico protocolos y que peticiones se van a permiti
        //Primero, indicamos qué protocolos de seguridad vamos a dejar activos o desactivar (CSRF) que que funciona con fo
        http.csrf(csrf -> csrf.disable()) //es un protocolo de seguridad cuando trabajamos sin un formulario de seguridad, entonces asi se desactiva el csrf
                //Luego, definimos que peticiones van a tener alguna regla
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/usuarios/**").permitAll()
                        .requestMatchers("api/productos/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); //va a trabajar con los parametros por defecto en la cabecera
        return http.build();
    }

    //Método que retorna el objeto encargado de otorgar al usuario que se logea
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUser() {
        UserDetails user = User.builder()
                .username("shrek")
                .password(passwordEncoder().encode("burro123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}


