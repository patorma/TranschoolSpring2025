package com.patricio.contreras.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.patricio.contreras.security.JWTConfigurer;
import com.patricio.contreras.security.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

	private final TokenProvider tokenProvider;

	// Define la configuración de seguridad para la aplicación.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// Configura CORS (Cross-Origin Resource Sharing) con valores predeterminados,
				// permitiendo a la aplicación web hacer solicitudes a otros dominios de manera segura.
				// CORS controla cómo los recursos web son solicitados desde diferentes dominios.
				.cors(Customizer.withDefaults())

				// Desactiva la protección CSRF (Cross-Site Request Forgery),
				// una medida de seguridad que previene que sitios maliciosos envíen solicitudes no autorizadas en nombre del usuario.
				// CSRF no es necesaria en aplicaciones que utilizan tokens JWT para autenticación.
				.csrf(AbstractHttpConfigurer::disable)

				// Configura las reglas de autorización de solicitudes.
				.authorizeHttpRequests((authz) -> authz
						// ¡IMPORTANTE! Coloca las rutas permitAll() al principio.
						// Permite el acceso sin autenticación a las rutas de registro y autenticación.
						.requestMatchers("/auth/sign-up-2", "/auth/sign-in","/auth/crear").permitAll()
						// Permite el acceso sin autenticación a las rutas de documentación Swagger.
						.requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
						.requestMatchers("/asignaciones/**").hasRole("ADMIN") // Requiere rol ADMIN para /asignaciones/**
						.requestMatchers("/admin/**").hasRole("ADMIN")    // Requiere rol ADMIN.+
						.requestMatchers("/furgones/**").hasRole("TRANSPORTISTA")
						.requestMatchers("/estudiantes/**").hasRole("APODERADO")
						// Requiere autenticación para cualquier otra solicitud.
						.anyRequest()
						.authenticated()
				)
			.exceptionHandling(ex ->ex
						.authenticationEntryPoint((request, response, authException) -> {
							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							response.setContentType("application/json");
							response.getWriter().write("{\"error\": \"Debe estar autenticado para acceder\"}");
						})
						.accessDeniedHandler((request,response,accessDeniedException)->{
							response.setStatus(HttpServletResponse.SC_FORBIDDEN);
							response.setContentType("application/json");
							response.getWriter().write("{\"error\": \"Usted no tiene permiso para acceder a este recurso\"}");
						}))

				// Configura la gestión de sesiones para que no se guarden en el servidor.
				.sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Añade el JWTConfigurer para manejar la autenticación basada en tokens JWT.
				.with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());

		return http.build();
	}

	// Define el encoder de contraseñas utilizado para cifrar las contraseñas de los usuarios.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}