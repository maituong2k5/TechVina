package com.phoneshop.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
//Bộ chuyển đổi thông tin JWT thành quyền trong Spring Security.
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	// Filter chịu trách nhiệm xác thực JWT.
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	// Xử lý lỗi khi người dùng chưa được xác thực.
	private final RestAuthenticationEntryPoint authenticationEntryPoint;

	// Constructor Injection.
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			RestAuthenticationEntryPoint authenticationEntryPoint) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	/**
	 * Cấu hình quy tắc bảo mật cho các REST API.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			JwtAuthenticationConverter cognitoJwtAuthenticationConverter) throws Exception {

		http
				// Tắt CSRF vì Backend sử dụng REST API và JWT.
				.csrf(AbstractHttpConfigurer::disable)

				// Kích hoạt CORS trong Spring Security.
				.cors(Customizer.withDefaults())

				// Cấu hình trả về HTTP 401 khi người dùng chưa được xác thực.
				.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))

				// Không sử dụng HTTP Session để lưu trạng thái đăng nhập.
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Cấu hình quyền truy cập API.
				.authorizeHttpRequests(auth -> auth

						// Cho phép kiểm tra sức khỏe Backend không cần JWT.
						.requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()

						// Cho phép đăng ký và đăng nhập mà không cần Access Token.
						.requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/refresh",
								"/api/auth/logout", "/api/san-pham/noi-bat")
						.permitAll()

						// Cho phép khách chưa đăng nhập xem chi tiết sản phẩm.
						.requestMatchers(HttpMethod.GET, "/api/san-pham/*/chi-tiet", "/api/san-pham/*/phien-ban",
								"/api/phien-ban/*/anh", "/api/danh-muc/active", "/api/thong-so-ky-thuat/**")
						.permitAll()

						// API dành riêng cho quản trị viên.
						.requestMatchers("/api/admin/**").hasRole(RoleConfig.ADMIN)

						// API dành cho nhân viên và quản trị viên.
						.requestMatchers("/api/nhan-vien/**").hasAnyRole(RoleConfig.STAFF, RoleConfig.ADMIN)

						// API dành cho khách hàng và quản trị viên.
						.requestMatchers("/api/khach-hang/**").hasAnyRole(RoleConfig.CUSTOMER, RoleConfig.ADMIN)

						// Các API còn lại bắt buộc phải xác thực.
						.anyRequest().authenticated())

				// Cấu hình xác thực JWT do Amazon Cognito cấp.
				.oauth2ResourceServer(oauth2 -> oauth2

						// Kích hoạt cơ chế xác thực JWT.
						.jwt(jwt -> jwt

								// Sử dụng bộ chuyển đổi quyền đã định nghĩa
								// trong CognitoRoleConfig.java.
								// Chuyển các nhóm Cognito thành quyền Spring Security:
								// ADMIN -> ROLE_ADMIN
								// STAFF -> ROLE_STAFF
								// CUSTOMER -> ROLE_CUSTOMER
								.jwtAuthenticationConverter(cognitoJwtAuthenticationConverter)));

		return http.build();
	}

	/**
	 * Ngăn Spring Boot tự động đăng ký JWT Filter vào Servlet Container.
	 *
	 * JWT Filter chỉ được thực thi thông qua Spring Security Filter Chain.
	 */
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(
			JwtAuthenticationFilter jwtAuthenticationFilter) {

		FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(
				jwtAuthenticationFilter);

		// Tắt đăng ký Filter tự động của Servlet Container.
		registration.setEnabled(false);

		return registration;
	}
}
