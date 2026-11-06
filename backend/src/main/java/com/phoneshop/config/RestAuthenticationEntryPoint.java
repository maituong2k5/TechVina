package com.phoneshop.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Xử lý các request chưa được xác thực.
 *
 * Trả về HTTP 401 khi người dùng chưa đăng nhập hoặc không cung cấp thông tin
 * xác thực hợp lệ.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		// Trả về HTTP 401 Unauthorized.
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bạn cần đăng nhập để truy cập tài nguyên này.");
	}
}