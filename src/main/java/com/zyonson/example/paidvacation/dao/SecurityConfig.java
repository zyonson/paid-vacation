package com.zyonson.example.paidvacation.dao;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {

		return new JdbcUserDetailsManager(dataSource);
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/clogin")
                .loginPage("/clogin")
                .defaultSuccessUrl("/home")
                .failureUrl("/clogin?error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/clogin")
        ).authorizeHttpRequests(authz -> authz
        		// "/css/**"などの静的ファイルはログインなしでアクセス可能
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // "/error"や"/"はログインなしでアクセス可能
                .requestMatchers("/error").permitAll()
                .requestMatchers("/").permitAll()
                // "/general"はRole_GENERALのみアクセス可能
                .requestMatchers("/general").hasRole("GENERAL")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).exceptionHandling(configurer ->
                 configurer.accessDeniedPage("/access-denied") // admin権限がないとエラーページに飛ぶ
        );

        return http.build();
    }

   // @Bean
   // public PasswordEncoder passwordEncoder() {
   //     return new BCryptPasswordEncoder();
   // }
}


