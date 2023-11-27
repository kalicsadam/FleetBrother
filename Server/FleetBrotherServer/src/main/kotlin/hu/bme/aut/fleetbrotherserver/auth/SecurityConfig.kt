package hu.bme.aut.fleetbrotherserver.auth

import hu.bme.aut.fleetbrotherserver.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter
) : GlobalMethodSecurityConfiguration() {
    @Bean
    fun authenticationManager(http: HttpSecurity, noOpPasswordEncoder: BCryptPasswordEncoder?): AuthenticationManager {
        val authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder::class.java)

        authenticationManagerBuilder.userDetailsService<UserDetailsService>(userDetailsService)
            .passwordEncoder(noOpPasswordEncoder)

        return authenticationManagerBuilder.build()
    }
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeRequests()
            .requestMatchers(AntPathRequestMatcher("/api/auth/checkToken")).authenticated()
            .requestMatchers(AntPathRequestMatcher("/api/auth/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher("/api/user/**")).hasRole("ADMIN")
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

//    @Bean
//    @Suppress("deprecation")
//    fun passwordEncoder(): NoOpPasswordEncoder {
//        return NoOpPasswordEncoder.getInstance() as NoOpPasswordEncoder
//    }

    @Bean
    @Suppress("deprecation")
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}