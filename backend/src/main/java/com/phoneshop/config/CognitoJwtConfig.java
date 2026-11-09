
package com.phoneshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * Cấu hình xác thực JWT do Amazon Cognito cấp.
 *
 * Nhiệm vụ: - Xác minh chữ ký JWT bằng khóa công khai của Cognito. - Kiểm tra
 * User Pool phát hành token. - Chỉ chấp nhận Access Token. - Kiểm tra token
 * thuộc App Client của TechVina.
 */
@Configuration
public class CognitoJwtConfig {

	// Lấy địa chỉ Cognito User Pool từ application.properties.
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuerUri;

	// Lấy App Client ID từ application.properties.
	@Value("${cognito.client-id}")
	private String clientId;

	/**
	 * Tạo JwtDecoder để Spring Security sử dụng khi xác thực JWT trong các API
	 * request.
	 */
	@Bean
	public JwtDecoder jwtDecoder() {

		// Tạo bộ giải mã JWT.
		// Spring Security sử dụng khóa công khai của Cognito
		// để xác minh chữ ký của token.
		NimbusJwtDecoder decoder = NimbusJwtDecoder.withIssuerLocation(issuerUri).build();

		// Kiểm tra token được phát hành bởi đúng User Pool.
		// Đồng thời kiểm tra các điều kiện mặc định
		// như thời gian hết hạn của token.
		OAuth2TokenValidator<Jwt> issuerValidator = JwtValidators.createDefaultWithIssuer(issuerUri);

		// Tạo bộ kiểm tra Access Token của TechVina.
		OAuth2TokenValidator<Jwt> accessTokenValidator = jwt -> {

			// Kiểm tra token_use.
			// Cognito có ID Token và Access Token.
			// Backend chỉ chấp nhận Access Token.
			if (!"access".equals(jwt.getClaimAsString("token_use"))) {

				// Từ chối nếu không phải Access Token.
				return OAuth2TokenValidatorResult
						.failure(new OAuth2Error("invalid_token", "Only Cognito Access Tokens are accepted", null));
			}

			// Kiểm tra Client ID trong JWT.
			// Đảm bảo token thuộc ứng dụng TechVina.
			if (!clientId.equals(jwt.getClaimAsString("client_id"))) {

				// Từ chối nếu Client ID không khớp.
				return OAuth2TokenValidatorResult
						.failure(new OAuth2Error("invalid_token", "Invalid Cognito App Client ID", null));
			}

			// Token vượt qua các kiểm tra bổ sung.
			return OAuth2TokenValidatorResult.success();
		};

		// Kết hợp các bộ kiểm tra JWT:
		// 1. User Pool và thời gian hiệu lực.
		// 2. token_use phải là "access".
		// 3. client_id phải khớp với TechVina.
		decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(issuerValidator, accessTokenValidator));

		// Trả về JwtDecoder để Spring Security sử dụng.
		return decoder;
	}
}
