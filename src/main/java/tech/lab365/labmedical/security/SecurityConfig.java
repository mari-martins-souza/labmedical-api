package tech.lab365.labmedical.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.security.core.Authentication;
import tech.lab365.labmedical.security.service.CustomUserDetailsService;
import tech.lab365.labmedical.security.service.UserToPatientService;
import org.springframework.security.authorization.AuthorizationDecision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    private final CustomUserDetailsService customUserDetailsService;

    private final UserToPatientService userToPatientService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, UserToPatientService userToPatientService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userToPatientService = userToPatientService;
    }



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
//                        .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "MEDICO")
//
//                        .requestMatchers(HttpMethod.GET, "/patients/{id}/medical-record").access((authentication, request) -> {
//
//                            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                            UUID userId = getUserIdFromAuthentication(auth);
//                            HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//                            UUID pathId = getPathVariableAsUUID(servletRequest, "id");
//                            UUID patientId = userToPatientService.findPatientIdByUserId(userId);
//                            return new AuthorizationDecision(patientId != null && patientId.equals(pathId));
//                        })
//                        .requestMatchers(HttpMethod.GET, "/patients/{id}/medical-record").hasAnyRole("ADMIN", "MEDICO")
//                        .anyRequest().authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }

    public UUID getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            logger.error("Authentication or principal is null");
            return null;
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userIdString = jwt.getClaim("sub");
        logger.info("Extracted user ID string from JWT: {}", userIdString);

        if (userIdString == null || userIdString.isEmpty()) {
            logger.error("User ID is null or empty");
            return null;
        }

        try {
            return UUID.fromString(userIdString);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID string: {}", userIdString, e);
            return null;
        }
    }

//    public UUID getPathVariableAsUUID(HttpServletRequest request, String variableName) {
//        Object attribute = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//
//        if (attribute == null) {
//            logger.error("Path variables attribute is null");
//            throw new IllegalArgumentException("Path variables are null");
//        }
//
//        if (!(attribute instanceof Map<?, ?> pathVariables)) {
//            logger.error("Path variables attribute is not of type Map");
//            throw new IllegalArgumentException("Path variables attribute is not of type Map");
//        }
//
//        if (!pathVariables.keySet().stream().allMatch(key -> key instanceof String) ||
//                !pathVariables.values().stream().allMatch(value -> value instanceof String)) {
//            logger.error("Path variables map contains non-string keys or values");
//            throw new IllegalArgumentException("Path variables map contains non-string keys or values");
//        }
//
//        @SuppressWarnings("unchecked")
//        Map<String, String> safePathVariables = (Map<String, String>) pathVariables;
//
//        String pathVariable = safePathVariables.get(variableName);
//        logger.info("Extracted path variable: " + pathVariable);
//
//        if (pathVariable == null || pathVariable.isEmpty()) {
//            logger.error("Path variable is null or empty");
//            throw new IllegalArgumentException("Path variable is null or empty");
//        }
//
//        try {
//            return UUID.fromString(pathVariable);
//        } catch (IllegalArgumentException e) {
//            logger.error("Invalid UUID string: " + pathVariable, e);
//            throw e;
//        }
//    }



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .addFilterAfter(new PathVariableFilter(), BasicAuthenticationFilter.class)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
//                        .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "MEDICO")
//                        .requestMatchers(HttpMethod.GET, "/patients/{id}/medical-record").access((authentication, request) -> {
//
//                            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                            UUID userId = getUserIdFromAuthentication(auth);
//                            if (userId == null) {
//                                return new AuthorizationDecision(false);
//                            }
//
//                            HttpServletRequest servletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//                            UUID pathId = getPathVariableAsUUID(servletRequest, "id");
//                            UUID patientId = userToPatientService.findPatientIdByUserId(userId);
//                            return new AuthorizationDecision(patientId != null && patientId.equals(pathId));
//                        })
//                        .anyRequest().authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                        .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/patients/{id}/medical-record").authenticated()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }





//    private UUID getUserIdFromAuthentication(Authentication authentication) {
//        Jwt jwt = (Jwt) authentication.getPrincipal();
//        return UUID.fromString(jwt.getClaimAsString("userId"));
//    }

//    private UUID getPathVariableAsUUID(HttpServletRequest request, String variableName) {
//        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        return UUID.fromString(pathVariables.get(variableName));
//    }


    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}


