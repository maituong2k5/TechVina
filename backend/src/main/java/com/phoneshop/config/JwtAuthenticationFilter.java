package com.phoneshop.config;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.phoneshop.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter xử lý xác thực JWT cho các HTTP Request.
 *
 * Mỗi request chỉ được Filter xử lý một lần.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// Service chuyên xử lý JWT.
	private final JwtService jwtService;

	/**
	 * Constructor Injection: Spring tự động cung cấp JwtService.
	 *
	 * @param jwtService service xử lý JWT
	 */
	public JwtAuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	/**
	 * Trích xuất JWT từ Authorization Header.
	 *
	 * @param request HTTP Request từ Client
	 * @return JWT nếu tồn tại, null nếu không có
	 */
	private String trichXuatToken(HttpServletRequest request) {

		// Lấy giá trị Authorization Header.
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		// Kiểm tra Header có tồn tại và bắt đầu bằng Bearer hay không.
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			return null;
		}

		// Loại bỏ tiền tố "Bearer " để lấy JWT.
		return authorizationHeader.substring(7);
	}

	/**
	 * Xử lý xác thực JWT cho mỗi HTTP Request.
	 *
	 * @param request     HTTP Request từ Client
	 * @param response    HTTP Response trả về Client
	 * @param filterChain chuỗi Filter tiếp theo
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Trích xuất Access Token từ HTTP Header.
		String token = trichXuatToken(request);

		// Nếu request không có Token, chuyển tiếp request.
		// Các API công khai vẫn có thể được truy cập.
		if (token == null) {
			filterChain.doFilter(request, response);
			return;
		}

		// Kiểm tra chữ ký và thời hạn của Access Token.
		if (!jwtService.kiemTraTokenHopLe(token)) {

			// Token không hợp lệ hoặc đã hết hạn.
			filterChain.doFilter(request, response);
			return;
		}

		// Trích xuất email và vai trò từ Access Token.
		String email = jwtService.trichXuatEmail(token);
		String vaiTro = jwtService.trichXuatVaiTro(token);

		// Tạo danh sách quyền từ vai trò trong JWT.
		// Spring Security yêu cầu quyền có tiền tố ROLE_.
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + vaiTro));

		// Tạo đối tượng Authentication đại diện cho người dùng đã xác thực.
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null,
				authorities);

		// Lưu thông tin xác thực vào SecurityContext của Spring Security.
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		// Tiếp tục xử lý request qua các filter tiếp theo.
		filterChain.doFilter(request, response);
	}

}