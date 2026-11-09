
package com.phoneshop.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class CognitoRoleConfig {

	// Danh sách các nhóm hợp lệ của hệ thống TechVina.
	private static final Set<String> ALLOWED_ROLES = Set.of("ADMIN", "STAFF", "CUSTOMER");

	/**
	 * Chuyển thông tin nhóm trong JWT Cognito thành quyền truy cập của Spring
	 * Security.
	 */
	@Bean
	public JwtAuthenticationConverter cognitoJwtAuthenticationConverter() {

		// Bộ chuyển đổi quyền OAuth2 mặc định.
		JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();

		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

		converter.setJwtGrantedAuthoritiesConverter(jwt -> {

			// Danh sách quyền của người dùng.
			Collection<GrantedAuthority> authorities = new ArrayList<>(scopeConverter.convert(jwt));

			// Lấy danh sách nhóm từ claim cognito:groups.
			List<String> groups = jwt.getClaimAsStringList("cognito:groups");

			if (groups != null) {
				for (String group : groups) {

					// Chỉ chấp nhận các nhóm đã định nghĩa.
					if (ALLOWED_ROLES.contains(group)) {

						// Chuyển ADMIN thành ROLE_ADMIN,
						// STAFF thành ROLE_STAFF,
						// CUSTOMER thành ROLE_CUSTOMER.
						authorities.add(new SimpleGrantedAuthority("ROLE_" + group));
					}
				}
			}

			// Trả về danh sách quyền để Spring Security sử dụng.
			return authorities;
		});

		return converter;
	}
}
