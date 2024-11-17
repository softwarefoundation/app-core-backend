package br.com.devchampions.backendapp.security.login.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Method: SecurityConfig.filterChain");

        habilitarAcessoSwagger(httpSecurity);

        habilitarAcessoAoConsoleH2(httpSecurity);

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated())
                .oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }


    private void habilitarAcessoSwagger(HttpSecurity http) throws Exception {
        String[] urls = {"/swagger-ui/index.html", "/swagger-ui/swagger-ui.css", "/swagger-ui/index.css", "/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/swagger-ui-standalone-preset.js", "/swagger-ui/swagger-initializer.js", "/swagger-ui/swagger-initializer.js", "/v3/api-docs/swagger-config","/v3/api-docs"};

        http.authorizeHttpRequests(
                auth -> auth.requestMatchers(urls).permitAll());
    }

    private void habilitarAcessoAoConsoleH2(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));

        httpSecurity.authorizeHttpRequests(
                auth -> auth.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll());
    }


    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        var jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
